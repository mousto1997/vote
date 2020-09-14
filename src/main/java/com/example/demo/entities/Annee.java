package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Annee {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnnee;
	@NotNull
	private int annee;
	@OneToMany(mappedBy="annee")
	private Collection<Session> sessions;
	public int getIdAnnee() {
		return idAnnee;
	}
	public void setIdAnnee(int idAnnee) {
		this.idAnnee = idAnnee;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public Collection<Session> getSessions() {
		return sessions;
	}
	public void setSessions(Collection<Session> sessions) {
		this.sessions = sessions;
	}
	public Annee(@NotEmpty int annee) {
		super();
		this.annee = annee;
	}
	public Annee() {
		super();
	}
	
	
	

}
