
package com.starstel.telcopro.accounts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sosthene Nouebissi
 */
@Entity
@Table(name = "approle")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole implements Serializable 
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "ROLE_SEQ", allocationSize = 1, name = "role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id")
    private Long id;
	
    private String roleName;
    private String description;
    
    @ManyToMany(cascade= {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(name = "APPROLE_APPMENU",
    		inverseJoinColumns = @JoinColumn(name = "MENU_ID", nullable = false, updatable = false),
    		joinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false),
    		foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
    		inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<AppMenu> menus = new ArrayList<>();
    
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }, mappedBy="roles")
    @JsonIgnore
    private List<AppUser> users = new ArrayList<>();

}
