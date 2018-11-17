
package com.starstel.telcopro.accounts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starstel.telcopro.rh.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sosthene Nouebissi
 */
@Entity
@Table(name = "appuser")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "USER_SEQ", allocationSize = 1, name = "user_id")
    @GeneratedValue(generator = "user_id")
    private Long id;
	
	@Column(unique = true)
	private String username;
    private String password;
    private String email;
    private Boolean lockStatus;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinColumn(name = "EMPL_ID")
    private Employee employee;
    
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE },
    		fetch = FetchType.EAGER )
    @JoinTable(name = "APPUSER_APPROLE",
    		inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false),
    		joinColumns = @JoinColumn(name = "USER_ID", nullable = false, updatable = false),
    		foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
    		inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<AppRole> roles = new ArrayList<>();

	public AppUser(String username, String password, String email, Boolean lockStatus) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.lockStatus = lockStatus;
	}
    
}
