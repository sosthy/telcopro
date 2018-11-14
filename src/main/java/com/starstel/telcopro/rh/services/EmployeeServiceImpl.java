package com.starstel.telcopro.rh.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.accounts.services.AccountService;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.repositories.EmployeeRepository;
import com.starstel.telcopro.stocks.entities.Mouvment;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private WorkSpaceService workSpaceService;
	
	@Override
	public List<Employee> listEmployee() 
	{
		return employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) 
	{
		employee.setWorkSpace(workSpaceService.saveWorkSpace(employee.getWorkSpace()));
		return employeeRepository.save(employee);
	}

	@Override
	public Employee editEmployee(Employee employee) 
	{
		return createEmployee(employee);
	}

	@Override
	public Boolean deleteEmployee(Long id) 
	{
		employeeRepository.deleteById(id);
		return true;
	}

	@Override
	public Set<Mouvment> listMouvmentOfEmployee(Long id) 
	{
		Employee employee = employeeRepository.findById(id).get();
		
		if(employee != null)
			return employee.getMouvments();
		else
			return null;
	}

	@Override
	public Employee employee(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public List<Employee> createEmployee(List<Employee> employees) {
		return employeeRepository.saveAll(employees);
	}

	@Override
	public List<Employee> searchEmployee(String keyWords) {
		return employeeRepository.search("%"+keyWords+"%");
	}

}
