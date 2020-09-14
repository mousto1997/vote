package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
public class Session {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSession;
	@NotEmpty
	private String nomSession;
	private Date dateDebut;
	private Date dateFin;
	@ManyToOne
	@JoinColumn(name="annee")
	private Annee annee;
	@ManyToOne
	@JoinColumn(name="mois")
	private Mois mois;
	@ManyToOne
	@JoinColumn(name="typeSession")
	private TypeSession typeSession;
	@OneToMany(mappedBy="sessions")
	private Collection<Pleniere> plenieres;
	public Collection<Pleniere> getPlenieres() {
		return plenieres;
	}
	public void setPlenieres(Collection<Pleniere> plenieres) {
		this.plenieres = plenieres;
	}
	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	public String getNomSession() {
		return nomSession;
	}
	public void setNomSession(String nomSession) {
		this.nomSession = nomSession;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Annee getAnnee() {
		return annee;
	}
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	public Mois getMois() {
		return mois;
	}
	public void setMois(Mois mois) {
		this.mois = mois;
	}
	public TypeSession getTypeSession() {
		return typeSession;
	}
	public void setTypeSession(TypeSession typeSession) {
		this.typeSession = typeSession;
	}
	public Session(@NotEmpty String nomSession, @NotEmpty Date dateDebut, Date dateFin, Annee annee, Mois mois,
			TypeSession typeSession) {
		super();
		this.nomSession = nomSession;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee = annee;
		this.mois = mois;
		this.typeSession = typeSession;
	}
	public Session() {
		super();
	}
	
	
}
