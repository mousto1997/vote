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
public class Mois {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMois;
	@NotNull
	private int moi;
	private String nomMoi;
	@OneToMany(mappedBy="mois")
	private Collection<Session> sessions;
	public int getIdMois() {
		return idMois;
	}
	public void setIdMois(int idMois) {
		this.idMois = idMois;
	}
	public int getMoi() {
		return moi;
	}
	public void setMoi(int moi) {
		this.moi = moi;
	}
	public String getNomMoi() {
		return nomMoi;
	}
	public void setNomMoi(String nomMoi) {
		this.nomMoi = nomMoi;
	}
	public Collection<Session> getSessions() {
		return sessions;
	}
	public void setSessions(Collection<Session> sessions) {
		this.sessions = sessions;
	}
	public Mois(@NotEmpty int moi, String nomMoi) {
		super();
		this.moi = moi;
		this.nomMoi = nomMoi;
	}
	public Mois(@NotEmpty int moi) {
		super();
		this.moi = moi;
	}
	public Mois() {
		super();
	}
	
	
	

}
