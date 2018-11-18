package com.starstel.telcopro.stocks.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starstel.telcopro.rh.entities.WorkSpace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Entrepot extends WorkSpace
{
	private Double nbOfProduct;
	private Double priceTotal;
	private Double volume;
	private Double volumeSecurity;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="entrepot")
	private Set<Emplacement> emplacements = new HashSet<>();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((emplacements == null) ? 0 : emplacements.hashCode());
		result = prime * result + ((nbOfProduct == null) ? 0 : nbOfProduct.hashCode());
		result = prime * result + ((priceTotal == null) ? 0 : priceTotal.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		result = prime * result + ((volumeSecurity == null) ? 0 : volumeSecurity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrepot other = (Entrepot) obj;
		if (emplacements == null) {
			if (other.emplacements != null)
				return false;
		} else if (!emplacements.equals(other.emplacements))
			return false;
		if (nbOfProduct == null) {
			if (other.nbOfProduct != null)
				return false;
		} else if (!nbOfProduct.equals(other.nbOfProduct))
			return false;
		if (priceTotal == null) {
			if (other.priceTotal != null)
				return false;
		} else if (!priceTotal.equals(other.priceTotal))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		if (volumeSecurity == null) {
			if (other.volumeSecurity != null)
				return false;
		} else if (!volumeSecurity.equals(other.volumeSecurity))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Entrepot [id=" + getId() + ", name=" + getName() + ", localisation=" + getLocalisation()
				+", nbOfProduct=" + nbOfProduct + ", priceTotal=" + priceTotal + ", volume=" + volume
				+ ", volumeSecurity=" + volumeSecurity + ", emplacements=" + emplacements
				+ ", id=" + getId() + ", name=" + getName() + ", localisation=" + getLocalisation()+ "]";
	}
	public Entrepot(String name, String localisation, Double nbOfProduct, Double priceTotal, Double volume,
			Double volumeSecurity) {
		super(name, localisation);
		this.nbOfProduct = nbOfProduct;
		this.priceTotal = priceTotal;
		this.volume = volume;
		this.volumeSecurity = volumeSecurity;
	}
	
	
	
	
}
