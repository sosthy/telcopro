package com.starstel.telcopro.storage.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@RequestMapping(value="/resources/download-pdf-file/{fileName}", method=RequestMethod.GET)
	public ResponseEntity<Object> getPdf(@PathVariable String fileName){

	    Resource resource = storager.getData(fileName);
	    long r = 0;
	    InputStream is=null;
	    HttpHeaders headers = new HttpHeaders();
	    
	    headers.add("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    
	    try {
	        is = resource.getInputStream();
	        r = resource.contentLength();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return ResponseEntity.ok().headers(headers).contentLength(r)
            .contentType(MediaType.parseMediaType("application/pdf"))
            .body(new InputStreamResource(is));

	}
}
