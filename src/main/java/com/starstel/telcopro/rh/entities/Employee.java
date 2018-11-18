
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
import javax.persistence.ManyToOne;
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
    private Date hiringDate;
    private Integer seniority;
    private String poste;
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Mouvment> mouvments;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private AppUser appUser;
    @ManyToOne
    private WorkSpace workSpace;
    
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


	public Employee(Long id, String name, String surname, String phone, String website, String sex, String cni,
			 String photo, Date birthday, Date hiring_date, Integer seniority, WorkSpace workSpace) {
		super(id, name, surname, phone, website, sex, cni, photo, birthday);
		this.hiringDate = hiring_date;
		this.seniority = seniority;
		this.workSpace = workSpace;
	}


	@Override
	public String toString() {
		return "Employee [id=" + getId() + ", hiring_date=" + hiringDate + ", anciennete=" + seniority + ", hashCode()="
				+ hashCode() + ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getWebsite()=" + getWebsite() 
				+ ", getSex()=" + getSex() + ", getCni()=" + getCni()
				+ ", getPhone()=" + getPhone() + ", getPhoto()=" + getPhoto() + ", getBirthday()=" + getBirthday()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
    
}
