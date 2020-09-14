package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Privillege_Depute implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPD;
	@JoinColumn(name="role")
	@ManyToOne
	private Role role;
	@JoinColumn(name="login")
	@ManyToOne
	private Depute login;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Depute getLogin() {
		return login;
	}
	public void setLogin(Depute login) {
		this.login = login;
	}
	public Privillege_Depute(Role role, Depute login) {
		super();
		this.role = role;
		this.login = login;
	}
	public Privillege_Depute() {
		super();
	}
	public int getIdPD() {
		return idPD;
	}
	public void setIdPD(int idPD) {
		this.idPD = idPD;
	}	
	
	
}
