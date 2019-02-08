package com.starstel.telcopro.rh.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starstel.telcopro.stocks.entities.Mouvment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author TCHECHE ROMEO
 */
@Entity
@DiscriminatorColumn(name = "workSpaceType", discriminatorType = DiscriminatorType.STRING, length = 255)
@DiscriminatorValue("Work Space")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class WorkSpace implements Serializable{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "WORK_SEQ", allocationSize = 1, name = "work_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_id")
	protected Long id;
	private String name;
	private String phone;
	private String email;
	private String website;
	private String location;
	@Column(name = "workSpaceType", insertable = false, updatable = false)
    private String workSpaceType;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="workSpaceReceiver") /*Elle se situe dans la classe mère pour permettre le transfert de produit d'un point de vente à un autre point de vente (Osé à un peut modifier*/
	private Set<Mouvment> mouvments = new HashSet<>();
	
    @OneToMany(cascade= CascadeType.ALL, mappedBy="workSpace")
	@JsonIgnore
	private List<Employee> employees= new ArrayList<>();
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		WorkSpace other = (WorkSpace) obj;
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	public WorkSpace(String name, String location, String phone, String email, String website) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.location = location;
	}
	
	
}
