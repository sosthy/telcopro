package com.starstel.telcopro.accounts.entities;

import java.util.ArrayList;
import java.util.List;

import com.starstel.telcopro.rh.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AppUserModel 
{
	private Long id;
	private String username;
    private String password;
    private String email;
    private Boolean lockStatus;
    private Employee employee;
    private List<AppRole> roles = new ArrayList<>();
    
    public AppUserModel(String username, String password, String email, Boolean lockStatus) 
    {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.lockStatus = lockStatus;
	}
}
