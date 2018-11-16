package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Emplacement implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "EMPLAC_SEQ", allocationSize = 1, name = "emplac_id")
    @GeneratedValue(generator = "emplac_id")
	private Long id;
	private String name;
	private String nbOfProduct;
	private String priceTotalOfProduct;
	@ManyToOne
	private Entrepot entrepot;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="emplacement")
	private Set<Product> products = new HashSet<>();
	
	public Emplacement(Long id, String name, Entrepot entrepot, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.entrepot = entrepot;
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Emplacement [id=" + id + ", name=" + name + ", entrepot=" + entrepot + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emplacement other = (Emplacement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
