package com.starstel.telcopro.storage.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
