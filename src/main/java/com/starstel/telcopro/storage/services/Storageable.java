package com.starstel.telcopro.storage.services;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.storage.entities.Storage;

public interface Storageable 
{
	public String store(MultipartFile file, Storage storage);
	
	public Resource loadFile(String fileName);
	
	public Path getFile(String fileName);
	
	public void deleteAll();
	
	public List<Resource> loadFiles(List<String> filesName);
	
	public boolean delete(String fileName);
	
	public void init();
	
	public Stream<Path> loadFiles();
}
