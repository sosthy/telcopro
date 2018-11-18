package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorValue;
import com.starstel.telcopro.rh.entities.WorkSpace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sosthene Nouebissi
 * @version 1.0
 * @created 11-oct.-2018 09:04:48
 */

@Entity
@DiscriminatorValue("Point Of Sale")
// @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PointOfSale extends WorkSpace{
	/* Spring à déranger sur ce coup. Il m'oblige à redefinir les constructeurs */
	public PointOfSale(String name, String localisation) {
		super(name, localisation);
	}
	public PointOfSale() {
		super();
	}
}