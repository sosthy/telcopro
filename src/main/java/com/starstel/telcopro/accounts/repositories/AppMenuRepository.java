package com.starstel.telcopro.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.starstel.telcopro.accounts.entities.AppMenu;

public interface AppMenuRepository extends JpaRepository<AppMenu, Long> 
{
	
}
