package com.starstel.telcopro.storage.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.storage.entities.Storage;

@Service
public class StorageImpl implements Storageable
{
	private final Path rootLocation = Storage.ROOT.getPath();

	@Override
	public String store(MultipartFile file, Storage storage)
	{	
		String fileName = "";
		if(file != null)
		{
			fileName = file.getOriginalFilename().replace(".", "_"+System.currentTimeMillis()+".");
			try
			{
				Files.copy(file.getInputStream(), storage.getPath().resolve(fileName));
			}
			catch(Exception e)
			{
				throw new RuntimeException("FAIL ! -> Message = " + e.getMessage());
			}
		}
		return fileName;
	}

	@Override
	public Resource loadFile(String fileName) 
	{
		try
		{
			Path file = getFile(fileName);
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
		catch(Exception e)
		{
			throw new RuntimeException("Error ! -> Message = " + e.getMessage());
		}
	}

	@Override
	public List<Resource> loadFiles(List<String> filesName) 
	{
		List<Resource> resources = new ArrayList<>();
		try
		{
			filesName.forEach(name -> {
				try {
					resources.add(loadFile(name));
				} catch (Exception e) {
				}
			});
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error ! -> Message = " + e.getMessage());
		}
		return resources;
	}

	@Override
	public void deleteAll() 
	{
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
	
	@Override
	public void init() 
	{
		for(Storage storage: Storage.values() ) 
		{
			try
			{ 
				Files.createDirectories(storage.getPath());
			}
			catch(IOException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public Stream<Path> loadFiles()
	{
		try
		{
			return Files.walk(this.rootLocation)
					.filter(path -> !path.equals(this.rootLocation) && !path.toFile().isDirectory());
		}
		catch(IOException e)
		{
			throw new RuntimeException("Failed to read stored file");
		}
	}

	@Override
	public Path getFile(String fileName) {
		return loadFiles().filter(path -> path.toFile().getName().equals(fileName)).findFirst().get();
	}

	@Override
	public boolean delete(String fileName) {
		try
		{
			return getFile(fileName).toFile().delete();
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to delete file "+fileName);
		}
	}
}
