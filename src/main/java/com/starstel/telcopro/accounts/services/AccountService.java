package com.starstel.telcopro.accounts.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.accounts.entities.AppMenu;
import com.starstel.telcopro.accounts.entities.AppRole;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.entities.AppUserModel;
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.stocks.entities.Product;

public interface AccountService 
{
	public boolean lockEmployee(Long id);
	public boolean unlockEmployee(Long id);
	
	public List<AppRole> listRoles();
	public AppRole getRole(Long id);
	public AppRole saveRole(AppRole appRole);
	public boolean deleteRole(Long id);
	public List<AppMenu> getMenusOfRole(Long id);
	
	public AppUser findUserByUsername(String username);
	public Long usersCount();
	public Long rolesCount();
	public AppUser saveUser(AppUserModel userModel);
	public List<Product> getProductsOfWorkSpace(AppUser user);
	
	public List<AppUser> listAppUsers();
	public AppUser getAppUser(Long id);
	public AppUser createUserAccount(Employee employee);
	public AppUser editAppUser(AppUser appUser);
	public boolean deleteAppUser(Long id);
	
	public AppMenu createAppMenu(AppMenu appMenu);
	public boolean deleteAppMenu(Long id);
	public List<AppMenu> getAppMenus();
	public AppMenu getAppMenu(Long id);
	List<AppUser> searchUsers(String keyWords);
	
}
