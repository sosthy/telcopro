
package com.starstel.telcopro.rh.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.stocks.entities.Mouvment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sosthene Nouebissi
 */
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Employee extends Person implements Serializable 
{
    private Date hiring_date;
    private Integer anciennete;
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Mouvment> mouvments;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private AppUser appUser;
    
    
    public void setAppUser(AppUser appUser)
    {
    	if(appUser == null)
    	{
    		if(this.appUser != null)
    		{
    			this.appUser.setEmployee(null);
    		}
    	}
    	else
    	{
    		appUser.setEmployee(this);
    	}
    	
    	this.appUser = appUser;
    }


	public Employee(Long id, String name, String surname, String portable, String website, String sex, String cni,
			String phone, String photo, Date birthday, Date hiring_date, Integer anciennete) {
		super(id, name, surname, portable, website, sex, cni, phone, photo, birthday);
		this.hiring_date = hiring_date;
		this.anciennete = anciennete;
	}


	@Override
	public String toString() {
		return "Employee [id=" + getId() + ", hiring_date=" + hiring_date + ", anciennete=" + anciennete + ", hashCode()="
				+ hashCode() + ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getPortable()="
				+ getPortable() + ", getWebsite()=" + getWebsite() + ", getSex()=" + getSex() + ", getCni()=" + getCni()
				+ ", getPhone()=" + getPhone() + ", getPhoto()=" + getPhoto() + ", getBirthday()=" + getBirthday()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
    
}
