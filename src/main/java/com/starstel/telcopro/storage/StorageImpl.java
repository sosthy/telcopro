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

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableCell;

@Service
public class StorageImpl implements Storageable
{
	@Autowired
	private EmployeeService employeeService;
	
	private final Path rootLocation = Storage.ROOT.getPath();
	private final int indexColumnExcelIdEmployees = 9;

	@Override
	public void store(MultipartFile file)
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

	
	@Override
	public List<Employee> readExcelEmployesFile(String path) {
		
		List<Employee> listEmployes= new ArrayList<>();
		File file= new File(path);

		Workbook wb;
		
		try {
			wb= Workbook.getWorkbook(file);
	
			Sheet sheet = wb.getSheet(0);

			SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
			
			Long id;
			
			for (int i = 1; i < sheet.getRows(); i++) {
				try {
					id= Long.parseLong(sheet.getCell(indexColumnExcelIdEmployees, i).getContents());
				} catch (NumberFormatException nfe) {
					id=null;
				}	

				listEmployes.add(new Employee(id, sheet.getCell(0, i).getContents(), sheet.getCell(1, i).getContents(), 
						sheet.getCell(5, i).getContents(), sheet.getCell(6, i).getContents(), sheet.getCell(4, i).getContents(), 
						sheet.getCell(3, i).getContents(), sheet.getCell(5, i).getContents(), sheet.getCell(7, i).getContents(), 
						df.parse(sheet.getCell(2, i).getContents()),df.parse(sheet.getCell(8, i).getContents()), 1));
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return listEmployes;

	}
	
	@Override
	public void updateExcelEmployesFile(String path, List<Employee> employees) {
		File file= new File(path), test=new File(Storage.DIRECTORY_DOCUMENTS.getName()+"/copy.xls");

		Workbook wb;
		WritableWorkbook wwb;
		
		try {
			wb= Workbook.getWorkbook(file);
			wwb= Workbook.createWorkbook(test,wb);
			WritableSheet sheet = wwb.getSheet(0);

			Label label;
			
			for (int i = 0; i < employees.size(); i++) {
				label = new Label(indexColumnExcelIdEmployees, i+1, ""+employees.get(i).getId());
				sheet.addCell(label);
			}
			/*
			wwb.write();
			wwb.close();
			wb.close();
			
			file.delete();
			test.renameTo(file);
			*/
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	@Override
	public void readEmployesFile(boolean updateFile) {
		
		if(updateFile)
		{
			updateExcelEmployesFile(Storage.EMPLOYEES_EXCEL_FILE.getName(), 
					employeeService.createEmployee(
							readExcelEmployesFile(Storage.EMPLOYEES_EXCEL_FILE.getName())));
		}
		else
		{
			employeeService.createEmployee(
					readExcelEmployesFile(Storage.EMPLOYEES_EXCEL_FILE.getName()));
		}
	}
}
