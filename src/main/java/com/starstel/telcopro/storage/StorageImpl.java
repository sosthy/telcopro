package com.starstel.telcopro.storage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.services.EmployeeService;

@Service
public class StorageImpl implements Storageable
{
	private final Path rootLocation = Storage.ROOT.getPath();

	@Override
	public void store(MultipartFile file)
	{
		if(file != null)
		{
			try
			{
				Files.copy(file.getInputStream(), 
						this.rootLocation.resolve(file.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
			}
			catch(Exception e)
			{
				throw new RuntimeException("FAIL ! -> Message = " + e.getMessage());
			}
		}
	}

	@Override
	public Resource loadFile(String fileName) 
	{
		try
		{
			Path file = rootLocation.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
		
			if(resource.exists() || resource.isReadable()) 
			{
				return resource;
			}
			else
			{
				throw new RuntimeException("Fail !");
			}
			
		}
		catch(MalformedURLException e)
		{
			throw new RuntimeException("Error ! -> Message = " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() 
	{
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
	
	@Override
	public void init() 
	{
		try
		{
			Files.createDirectory(Storage.ROOT.getPath());
			Files.createDirectory(Storage.DIRECTORY_IMAGES.getPath());
			Files.createDirectory(Storage.DIRECTORY_DOCUMENTS.getPath());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
			throw new RuntimeException("Could Not Initialize Storage !");
		}
	}

	@Override
	public Stream<Path> loadFiles()
	{
		try
		{
			return Files.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		}
		catch(IOException e)
		{
			throw new RuntimeException("\"Failed to read stored file");
		}
	}
}
