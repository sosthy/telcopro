package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class MouvmentLine implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "TRANSL_SEQ", allocationSize = 1, name = "stransl_id")
    @GeneratedValue(generator = "stransl_id")
	private Long id;
	private Double quantity;
	private Double priceUnit;
	private Double priceTotal;
	@ManyToOne @JsonIgnore
	private Mouvment mouvment;
	@ManyToOne
	private Product product;
	@ManyToMany
	@JoinTable(name="mouvlineProdItem",
	    joinColumns=@JoinColumn(name="mouvlines"),
	    inverseJoinColumns=@JoinColumn(name="prodsitems")
	  )
	private Set<PortableItem> productsItem;
	private String note;
}
