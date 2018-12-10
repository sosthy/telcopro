package com.starstel.telcopro.storage.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starstel.telcopro.storage.services.Storageable;

@CrossOrigin("*")
@RestController
public class StorageRestController {
	
	@Autowired
	private Storageable storager;
	
	@RequestMapping(value = "/resources/{filename:.+}", method=RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename)
	{
		System.out.println(filename);
		Resource file = storager.loadFile(filename);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
					.body(file);
	}
	
	@RequestMapping(value = "/resources", method=RequestMethod.GET)
	public List<ResponseEntity<Resource>> downloadFiles(@RequestBody List<String> filesName)
	{
		List<ResponseEntity<Resource>> responseEntities = new ArrayList<>();
		
		storager.loadFiles(filesName).forEach(resource -> {
			responseEntities.add(ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)).body(resource));
		});
		
		return responseEntities;
	}
	
	@RequestMapping(value = "/resources/{filename:.+}", method=RequestMethod.DELETE)
	public boolean deleteFile(@PathVariable String filename)
	{
		return storager.delete(filename);
	} /*
	
	@RequestMapping(value = "/resources", method=RequestMethod.GET)
	public boolean downFiles()
	{
		List<Resource> resources = storager.loadFiles();
		return storager.delete(filename);
	}*/
}
