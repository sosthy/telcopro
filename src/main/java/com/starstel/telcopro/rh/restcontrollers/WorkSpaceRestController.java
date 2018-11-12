package com.starstel.telcopro.rh.restcontrollers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.entities.WorkSpace;
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.rh.services.WorkSpaceService;
import com.starstel.telcopro.stocks.entities.Mouvment;

@CrossOrigin("*")
@RestController
@RequestMapping("rh/workSpaces")
public class WorkSpaceRestController 
{
	@Autowired
	private WorkSpaceService workSpaceService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<WorkSpace> getAllWorkSpace()
	{
		return workSpaceService.listWorkSpace();
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<WorkSpace> searchWorkSpace(@RequestParam(name="mc", defaultValue="") String keyWords)
	{
		return workSpaceService.searchWorkSpace(keyWords);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public WorkSpace getWorkSpace(@PathVariable Long id)
	{
		return workSpaceService.getWorkSpace(id);
	}
	
	@RequestMapping(value="/mouvment-of-workSpace/{id}", method = RequestMethod.GET)
	public List<Mouvment> getAllMouvmentOfWorkSpace(@PathVariable Long id)
	{
		return workSpaceService.listMouvmentOfWorkSpace(id);
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public WorkSpace saveWorkSpace(@RequestBody WorkSpace workSpace)
	{
		System.err.println(workSpace);
		return workSpaceService.saveWorkSpace(workSpace);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Boolean deleteWorkSpace(@PathVariable Long id)
	{
		return workSpaceService.deleteWorkSpace(id);
	}
}
