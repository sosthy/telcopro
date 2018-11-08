package com.starstel.telcopro.stocks.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @version 1.0
 * @created 13-oct.-2018 07:24:16
 */
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PortableCategory extends Category 
{
	@OneToMany(cascade=CascadeType.ALL, mappedBy="portableCategory")
	@JsonIgnore
	public Set<Portable> portables = new HashSet<>();

	public PortableCategory(Long id, String name, String notes) {
		super(id, name, notes);
	}
	
	
}