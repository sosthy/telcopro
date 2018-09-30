package com.starstel.telcopro;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.starstel.telcopro.accounts.entities.AppRole;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.services.AccountService;
import com.starstel.telcopro.rh.entities.Employee;

@SpringBootApplication
public class TelcoproApplication implements CommandLineRunner
{
	@Autowired
	private AccountService accountService;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(TelcoproApplication.class, args);
	}
	
	@Bean // Instanciation de BCryptPasswordEncoder
	public BCryptPasswordEncoder getBCPE() 
	{	
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception 
	{	
		
		AppRole admin = new AppRole();
		admin.setRoleName("ADMIN");
		admin.setDescription("No Description");
		admin = accountService.createRole(admin);
		
		AppRole simple = new AppRole();
		simple.setRoleName("SIMPLE");
		simple.setDescription("No Description");
		simple = accountService.createRole(simple);
		
		AppUser user = new AppUser();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setLock_status(false);
		user.setEmail("sosthenegolden@gmail.com");
		user.getRoles().add(admin);
		user.getRoles().add(simple);
		
		Employee employee = new Employee();
		employee.setName("NOUEBISSI NGHEMNIN");
		employee.setSurname("Sosthene");
		employee.setBirth_date(new Date());
		employee.setHiring_date(new Date());
		employee.setPhone("655321007");
		employee.setSex("M");
		employee.setAnciennete(4);
		employee.setPhoto("XXXX");
		employee.setCni("KIT272");
		employee.setAppUser(user);
		accountService.createEmployee(employee);
	}
}
