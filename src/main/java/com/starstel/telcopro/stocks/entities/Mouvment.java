package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.Date;
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
import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.entities.WorkSpace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Mouvment implements Serializable
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "TRANS_SEQ", allocationSize = 1, name = "trans_id")
    @GeneratedValue(generator = "trans_id")
	private Long id;
	private String reference;
	private Date date;
	private Double quantity;
	private Double priceTotal;
	@ManyToOne
	private WorkSpace workSpaceSource;
	@ManyToOne
	private WorkSpace workSpaceRecipient;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="mouvment")
	private Set<MouvmentLine> mouvmentLines = new HashSet<>();
	@ManyToOne
	private MouvmentType mouvmentType;
	@ManyToOne
	private Employee user;
	@ManyToOne
	private Recipient recipient;
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priceTotal == null) ? 0 : priceTotal.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		Mouvment other = (Mouvment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priceTotal == null) {
			if (other.priceTotal != null)
				return false;
		} else if (!priceTotal.equals(other.priceTotal))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}
	
	
	
}
