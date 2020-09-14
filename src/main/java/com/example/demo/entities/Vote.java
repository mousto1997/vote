package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
@Entity
public class Vote {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVote;
	private Date dateVote;
	private Date dateHeureOuverture;
	private Date dateHeureFermeture;
	private String choix;
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	@ManyToOne
	@JoinColumn(name="deputes")
	private Depute deputes;
	@ManyToOne
	@JoinColumn(name="texteLois")
	private TextLoi texteLois;
	public int getIdVote() {
		return idVote;
	}
	public void setIdVote(int idVote) {
		this.idVote = idVote;
	}
	public Date getDateVote() {
		return dateVote;
	}
	public void setDateVote(Date dateVote) {
		this.dateVote = dateVote;
	}
	public Date getDateHeureOuverture() {
		return dateHeureOuverture;
	}
	public void setDateHeureOuverture(Date dateHeureOuverture) {
		this.dateHeureOuverture = dateHeureOuverture;
	}
	public Date getDateHeureFermeture() {
		return dateHeureFermeture;
	}
	public void setDateHeureFermeture(Date dateHeureFermeture) {
		this.dateHeureFermeture = dateHeureFermeture;
	}
	public Depute getDeputes() {
		return deputes;
	}
	public void setDeputes(Depute deputes) {
		this.deputes = deputes;
	}
	public TextLoi getTexteLois() {
		return texteLois;
	}
	public void setTexteLois(TextLoi texteLois) {
		this.texteLois = texteLois;
	}
	
	
	public Vote(Date dateVote, Date dateHeureOuverture, Date dateHeureFermeture, String choix, Depute deputes,
			TextLoi texteLois) {
		super();
		this.dateVote = dateVote;
		this.dateHeureOuverture = dateHeureOuverture;
		this.dateHeureFermeture = dateHeureFermeture;
		this.choix = choix;
		this.deputes = deputes;
		this.texteLois = texteLois;
	}
	public Vote(@NotEmpty Date dateVote, Date dateHeureOuverture, Date dateHeureFermeture, Depute deputes,
			TextLoi texteLois) {
		super();
		this.dateVote = dateVote;
		this.dateHeureOuverture = dateHeureOuverture;
		this.dateHeureFermeture = dateHeureFermeture;
		this.deputes = deputes;
		this.texteLois = texteLois;
	}
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
