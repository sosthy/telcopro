package com.starstel.telcopro.rh.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.stocks.entities.Mouvment;

public interface EmployeeService 
{
	List<Employee> listEmployee();
	Employee employee(Long id);
	Employee createEmployee(Employee employee);
	List<Employee> createEmployee(List<Employee> employees);
	Employee editEmployee(Employee employee);
	Boolean deleteEmployee(Long id);
	Set<Mouvment> listMouvmentOfEmployee(Long id);
	List<Employee> searchEmployee(String keyWords);
}
