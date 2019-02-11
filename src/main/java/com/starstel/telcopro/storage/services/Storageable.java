package com.starstel.telcopro.storage.services;

import java.io.File;
import java.util.List;

import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.storage.entities.Storage;

public interface Storageable 
{
	public String store(MultipartFile file, Storage storage);
	
	public String loadFile(String fileName);
	
	public File getFile(String fileName);
	
	public void deleteAll();
	
	public List<String> loadFiles(Storage storage);
	
	public boolean delete(String fileName);
	
	public void init();
	
	public List<String> loadFiles();

	public Resource getData(String filename);
	
	public boolean fileExists(File file);
}
