package com.starstel.telcopro.accounts.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starstel.telcopro.accounts.entities.AppMenu;
import com.starstel.telcopro.accounts.entities.AppRole;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.entities.AppUserModel;
import com.starstel.telcopro.accounts.repositories.AppMenuRepository;
import com.starstel.telcopro.accounts.repositories.AppRoleRepository;
import com.starstel.telcopro.accounts.repositories.AppUserRepository;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.repositories.EmployeeRepository;
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.services.EntrepotService;

@Service
@Transactional 
public class AccountServiceImpl implements AccountService 
{	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private AppMenuRepository appMenuRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EntrepotService entrepotService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	public boolean lockEmployee(Long id) 
	{
		Employee employee = employeeService.employee(id);
		employee.getAppUser().setLockStatus(true);
		employeeService.createEmployee(employee); // Mis à jour de l'employé
		return true;
	}
	
	@Override
	public boolean unlockEmployee(Long id) 
	{
		Employee employee = employeeService.employee(id);
		employee.getAppUser().setLockStatus(false);
		employeeService.createEmployee(employee); // Mis à jour de l'employé
		return true;
	}
	
	@Override
	public List<AppRole> listRoles() 
	{
		return appRoleRepository.findAll();
	}
	
	@Override
	public AppRole getRole(Long id) 
	{
		return appRoleRepository.getOne(id);
	}
	
	@Override
	public AppRole saveRole(AppRole role) 
	{
		return appRoleRepository.save(role);
	}
	
	@Override
	public boolean deleteRole(Long id) 
	{
		AppRole role = appRoleRepository.findById(id).get();
		role.getUsers().forEach(user -> {
			user.getRoles().remove(role);
		});
		appRoleRepository.delete(role);
		return true;
	}
	
	@Override
	public Long usersCount()
	{
		return appUserRepository.count();
	}
	
	@Override
	public Long rolesCount()
	{
		return appRoleRepository.count();
	}
	
	@Override
	public AppUser findUserByUsername(String username) 
	{
		return appUserRepository.findByUsername(username);
	}

	@Override
	public AppUser saveUser(AppUserModel userModel) 
	{
		AppUser user = new AppUser();
		user.setId(userModel.getId());
		user.setUsername(userModel.getUsername());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setEmail(userModel.getEmail());
		user.setEmployee(userModel.getEmployee());
		user.setLockStatus(userModel.getLockStatus());
		user.setRoles(userModel.getRoles());
		
		user= appUserRepository.save(user);
		
		user.getEmployee().setAppUser(user);
		employeeService.createEmployee(user.getEmployee()); // Mis à jour de l'employé
		
		return user;
	}

	@Override
	public List<AppUser> listAppUsers() {
		return appUserRepository.findAll();
	}

	@Override
	public AppUser getAppUser(Long id) {
		return appUserRepository.findById(id).get();
	}

	@Override
	public AppUser createUserAccount(Employee employee) {
		
		AppUser appUser= employee.getAppUser();
		
		appUser.setEmployee(employee);
		appUser=appUserRepository.save(appUser);
		
		employee.setAppUser(appUser);
		employee= employeeService.createEmployee(employee); // Mis à jour de l'employé
		
		return appUser;
	}

	@Override
	public AppUser editAppUser(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	@Override
	public boolean deleteAppUser(Long id) {
		appUserRepository.deleteById(id);
		return true;
	}

	@Override
	public AppMenu createAppMenu(AppMenu appMenu) {
		return appMenuRepository.save(appMenu);
	}

	@Override
	public boolean deleteAppMenu(Long id) {
		appMenuRepository.deleteById(id);
		return true;
	}

	@Override
	public List<AppMenu> getAppMenus() {
		return appMenuRepository.findAll();
	}

	@Override
	public AppMenu getAppMenu(Long id) {
		return appMenuRepository.findById(id).get();
	}

	@Override
	public List<AppMenu> getMenusOfRole(Long id) 
	{
		AppRole role = appRoleRepository.findById(id).get();
		
		if(role != null) 
		{
			return role.getMenus();
		}
		
		return null;
	}

	@Override
	public List<AppUser> searchUsers(String keyWords) {
		System.err.println("%"+keyWords+"%");
		return appUserRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<Product> getProductsOfWorkSpace(AppUser user) {
		return entrepotService.getProductsOfEntrepot(user.getEmployee().getWorkSpace().getId());
	}
}
