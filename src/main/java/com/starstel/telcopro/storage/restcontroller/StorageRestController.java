package com.starstel.telcopro.storage.restcontroller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starstel.telcopro.storage.entities.Storage;
import com.starstel.telcopro.storage.services.Storageable;

@CrossOrigin("*")
@RestController
public class StorageRestController {
	
	@Autowired
	private Storageable storager;
	
	@RequestMapping(value = "/resources/download-file/{filename:.+}", method=RequestMethod.GET)
	public ResponseEntity<String> downloadFile(@PathVariable String filename)
	{
		return new ResponseEntity<String>(storager.loadFile(filename), HttpStatus.OK);
	}
	
	@RequestMapping(value="/resources/download-files/{store}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getImages(@PathVariable String store) {
		return new ResponseEntity<List<String>>(storager.loadFiles(Storage.valueOf(store)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resources/{filename:.+}", method=RequestMethod.DELETE)
	public boolean deleteFile(@PathVariable String filename)
	{
		return storager.delete(filename);
	} 
	
	@RequestMapping(value="/resources/download/{fileName}", method=RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable String fileName)
	{
		System.out.println(fileName);
		Resource file = storager.getData(fileName);
		/*
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
				.body(file);*/
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	@GetMapping(value = "/api/image/logo")
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
 
        ClassPathResource imgFile = new ClassPathResource("image/grokonez-logo.png");
 
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
