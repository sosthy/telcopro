package com.starstel.telcopro.rh.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.repositories.AppUserRepository;
import com.starstel.telcopro.accounts.services.AccountService;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.repositories.EmployeeRepository;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.storage.entities.Storage;
import com.starstel.telcopro.storage.services.Storageable;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private Storageable storager;
	
	
	@Override
	public List<Employee> listEmployee() 
	{
		return employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) 
	{
		try {
			if (employee.getId() != null && employee.getAppUser() == null )
			{
				Employee emp = employee(employee.getId());
				employee.setAppUser(emp.getAppUser());
				employee.setMouvments(emp.getMouvments());
			}
			Period period = Period.between(employee.getHiringDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
								LocalDate.now());
			employee.setSeniority(period.getYears());
			return employeeRepository.save(employee);
		} catch (Exception e) {
			throw new RuntimeException("Fail to save Employee "+employee.getName()+". Raison -> "+e.getMessage());
		}
	}

	@Override
	public Employee createEmployee(MultipartFile employeeImageFile, Employee employee) 
	{
		String photoName = storager.store(employeeImageFile, Storage.DIRECTORY_EMPLOYEES_IMAGES);
		try {
			if (employee.getId() == null) {
				employee.setPhoto(photoName);
			}
			else if(employeeImageFile != null) {
					storager.delete(employee.getPhoto());
					employee.setPhoto(photoName);
				}
				else {
					if (employee.getPhoto().isEmpty()) {
						Employee emp = employee(employee.getId());
						if(!emp.getPhoto().isEmpty()) 
							storager.delete(emp.getPhoto());
					}
				}
			return createEmployee(employee);
		} catch (Exception e) {
			throw new RuntimeException("Fail to save Employee "+employee.getName()+". Raison -> "+e.getMessage());
		}
	}
	
	@Override
	public Boolean deleteEmployee(Long id) 
	{
		String photoName = employee(id).getPhoto();
		employeeRepository.deleteById(id);
		if(!photoName.isEmpty()) 
			storager.delete(photoName);
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

	@Override
	public Employee getEmployeeByUsername(Long id) {
		AppUser user = this.appUserRepository.findById(id).get();
		return user.getEmployee();
	}

}
