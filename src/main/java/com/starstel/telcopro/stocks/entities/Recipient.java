package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starstel.telcopro.rh.entities.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Recipient implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "REC_SEQ", allocationSize = 1, name = "rec_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_id")
    private Long id;
	
	private String designation;
    private String location;
    private String website;
    private String phone;
    @ManyToOne
    private RecipientGroupe groupe;
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="recipient")
    private Set<Mouvment> mouvments = new HashSet<>();
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = super.hashCode();
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Recipient other = (Recipient) obj;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	public Recipient(String designation, String location, String website, String phone) {
		super();
		this.designation = designation;
		this.location = location;
		this.website = website;
		this.phone = phone;
	}
    
    
}
