package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class TypeSession {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTypeSession;
	@NotEmpty
	private String libelle;
	@OneToMany(mappedBy="typeSession")
	private Collection<Session> session;
	public int getIdTypeSession() {
		return idTypeSession;
	}
	public void setIdTypeSession(int idTypeSession) {
		this.idTypeSession = idTypeSession;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<Session> getSession() {
		return session;
	}
	public void setSession(Collection<Session> session) {
		this.session = session;
	}
	public TypeSession(@NotEmpty String libelle) {
		super();
		this.libelle = libelle;
	}
	public TypeSession() {
		super();
	}
	
	

}
