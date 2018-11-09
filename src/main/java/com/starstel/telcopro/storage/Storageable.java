package com.starstel.telcopro.storage;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.rh.entities.Employee;

public interface Storageable 
{
	public void store(MultipartFile file);
	
	public Resource loadFile(String fileName);
	
	public void deleteAll();
	
	public void init();
	
	public Stream<Path> loadFiles();
}
