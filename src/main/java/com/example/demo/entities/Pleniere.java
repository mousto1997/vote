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
@Entity
public class Pleniere {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPleniere;
	@NotEmpty
	private String ordreDuJour;
	@NotEmpty
	private String president;
	@NotEmpty
	private String secretaire;
	private Date datePleniere;
	@ManyToOne
	@JoinColumn(name="session")
	private Session sessions;
	@OneToMany(mappedBy="plenieres")
	private Collection<TextLoi> textLois;
	public int getIdPleniere() {
		return idPleniere;
	}
	public void setIdPleniere(int idPleniere) {
		this.idPleniere = idPleniere;
	}
	public String getOrdreDuJour() {
		return ordreDuJour;
	}
	public void setOrdreDuJour(String ordreDuJour) {
		this.ordreDuJour = ordreDuJour;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getSecretaire() {
		return secretaire;
	}
	public void setSecretaire(String secretaire) {
		this.secretaire = secretaire;
	}
	public Date getDatePleniere() {
		return datePleniere;
	}
	public void setDatePleniere(Date datePleniere) {
		this.datePleniere = datePleniere;
	}
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public Collection<TextLoi> getTextLois() {
		return textLois;
	}
	public void setTextLois(Collection<TextLoi> textLois) {
		this.textLois = textLois;
	}
	public Pleniere(@NotEmpty String ordreDuJour, @NotEmpty String president, @NotEmpty String secretaire,
			Date datePleniere, Session sessions) {
		super();
		this.ordreDuJour = ordreDuJour;
		this.president = president;
		this.secretaire = secretaire;
		this.datePleniere = datePleniere;
		this.sessions = sessions;
	}
	public Pleniere() {
		super();
	}
	
	
	
	
	
	

}
