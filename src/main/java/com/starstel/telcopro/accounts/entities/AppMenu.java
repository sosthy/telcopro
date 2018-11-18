package com.starstel.telcopro.accounts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appmenu")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppMenu implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "MENU_SEQ", allocationSize = 1, name = "menu_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id")
    private Long id;
	
    private String name;
    private String icon;
    private String link;
    private String color;
    private String description;
    
    @ManyToMany(mappedBy="menus")
    @JsonIgnore
    private List<AppRole> roles = new ArrayList<>();

    
	public AppMenu(String name, String icon, String link, String color, String description) 
	{
		this.name = name;
		this.icon = icon;
		this.link = link;
		this.color = color;
		this.description = description;
	}
}
