package com.starstel.telcopro.storage.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;
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
				Files.copy(file.getInputStream(), storage.getPath().resolve(fileName), 
						StandardCopyOption.REPLACE_EXISTING);
			}
			catch(Exception e)
			{
				throw new RuntimeException("FAIL ! -> Message = " + e.getMessage());
			}
		}
		return fileName;
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
	public List<String> loadFiles()
	{
		List<String> files = new ArrayList<String>();
		
		getStoreFiles().forEach(file -> {
			files.add(encodeToString(file.toFile()));
		});
		return files;
	}

	@Override
	public File getFile(String fileName) {
		try {
			return getStoreFiles().filter(path -> path.toFile().getName().equals(fileName)).findFirst().get().toFile();
		} catch (Exception e) {
			throw new RuntimeException("Can't found File: "+fileName+ ". Raison: -> " + e.getMessage());
		}
	}

	@Override
	public boolean delete(String fileName) {
		try
		{
			return getFile(fileName).delete();
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to delete file "+fileName+ ". Raison: -> " + e.getMessage());
		}
	}

	@Override
	public String loadFile(String fileName) {
		return encodeToString(getFile(fileName));
	}

	@Override
	public List<String> loadFiles(Storage storage) {
		List<String> images = new ArrayList<String>();
		File fileFolder = new File(storage.getName());
		if(fileFolder != null) {
			for(final File file: fileFolder.listFiles()) {
				if(!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int)file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add(file.getName()+"$data:image/"+extension+";base64,"+encodeBase64);
						fileInputStream.close();
					}catch(Exception e) {
						
					}
				}
			}
		}
		return images;
	}
	
	public String encodeToString(File file) {
		String encodeBase64 = null;
		try {
			String extension = FilenameUtils.getExtension(file.getName());
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			fileInputStream.read(bytes);
			encodeBase64 = Base64.getEncoder().encodeToString(bytes);
			fileInputStream.close();
			return file.getName()+"$data:image/"+extension+";base64,"+encodeBase64;
		}catch(Exception e) {
			throw new RuntimeException("Fail to encoding file " + file.getName() + ". Raison: -> " + e.getMessage());
		}
	}

	public Stream<Path> getStoreFiles()
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
	public Resource getData(String fileName) {
		try
		{
			Path file = getFile(fileName).toPath();
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
	public boolean fileExists(File file) {
		try {
			getFile(file.getName());
    		System.err.println("File: " + file.getName() + " exists.");
			return true;
		} catch (Exception e) {
    		System.err.println("File: " + file.getName() + " not exists.");
		}
		return false;
	}
}
