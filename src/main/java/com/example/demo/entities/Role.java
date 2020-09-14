package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class Role implements Serializable{
	@Id @NotEmpty
	private String role;
	@OneToMany(mappedBy="role", cascade=CascadeType.REMOVE)
	private Collection<Privillege_Depute> roles;
	public Role(@NotEmpty String role) {
		super();
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Collection<Privillege_Depute> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Privillege_Depute> roles) {
		this.roles = roles;
	}
	public Role() {
		super();
	}
	
	

}
