package com.starstel.telcopro.accounts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.accounts.entities.AppMenu;

public interface AppMenuRepository extends JpaRepository<AppMenu, Long> 
{
	@Query("select distinct menu from AppMenu menu inner join menu.roles role inner join role.users user where user.username like :x "
			+ "order by menu.name")
	public List<AppMenu> findMenuUser(@Param("x") String username);
}
