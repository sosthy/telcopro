package com.starstel.telcopro.accounts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.accounts.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> 
{
	@Query("select u from AppUser u where lower(u.username) like lower(?1)")
	public AppUser findByUsername(String username);
	@Query("select u from AppUser u where lower(u.username) like lower(:x) or lower(u.email) like lower(:x)")
	public List<AppUser> search(@Param("x") String keyWords);
}
