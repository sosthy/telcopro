package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MeasureUnit implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "MES_SEQ", allocationSize = 1, name = "mes_id")
    @GeneratedValue(generator = "mes_id")
	private Long id;
	private String unity;
	@OneToMany(cascade= CascadeType.ALL, mappedBy="measureUnit")
	@JsonIgnore
	private Set<Product> productList = new HashSet<>();
	
	public MeasureUnit(String unity) {
		super();
		this.unity = unity;
	}
	
	
}
