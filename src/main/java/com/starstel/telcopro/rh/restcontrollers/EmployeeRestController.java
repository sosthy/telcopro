package com.starstel.telcopro.rh.restcontrollers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.storage.entities.Storage;
import com.starstel.telcopro.storage.services.Storageable;

@CrossOrigin("*")
@RestController
@RequestMapping("rh/employees")
public class EmployeeRestController 
{
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Employee> getAllEmployee()
	{
		return employeeService.listEmployee();
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<Employee> searchEmployee(@RequestParam(name="mc", defaultValue="") String keyWords)
	{
		return employeeService.searchEmployee(keyWords);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable Long id)
	{
		return employeeService.employee(id);
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public Employee getEmployeeByUsername(@PathVariable Long id)
	{
		return employeeService.getEmployeeByUsername(id);
	}
	
	@RequestMapping(value="/mouvment-of-employee/{id}", method = RequestMethod.GET)
	public Set<Mouvment> getAllMouvmentOfEmployee(@PathVariable Long id)
	{
		return employeeService.listMouvmentOfEmployee(id);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public Employee save(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("employee") String employeeJsonStringify)
	{
		try 
		{
			Employee employee = new ObjectMapper().readValue(employeeJsonStringify, Employee.class);
			
			return employeeService.createEmployee(file, employee);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Boolean deleteEmployee(@PathVariable Long id)
	{
		return employeeService.deleteEmployee(id);
	}
}
