package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class AmendementDepute {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAmandDepute;
	private String objetViser;
	@NotEmpty
	private String libelle;
	@NotEmpty
	private String contenu;
	@ManyToOne
	@JoinColumn(name="depute")
	private Depute deputes;
	@ManyToOne
	@JoinColumn(name="texteLoi")
	private TextLoi texteLois;
	public int getIdAmandDepute() {
		return idAmandDepute;
	}
	public void setIdAmandDepute(int idAmandDepute) {
		this.idAmandDepute = idAmandDepute;
	}
	public String getObjetViser() {
		return objetViser;
	}
	public void setObjetViser(String objetViser) {
		this.objetViser = objetViser;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
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
	public AmendementDepute(String objetViser, @NotEmpty String libelle, @NotEmpty String contenu, Depute deputes,
			TextLoi texteLois) {
		super();
		this.objetViser = objetViser;
		this.libelle = libelle;
		this.contenu = contenu;
		this.deputes = deputes;
		this.texteLois = texteLois;
	}
	public AmendementDepute() {
		super();
	}
	
	
}
