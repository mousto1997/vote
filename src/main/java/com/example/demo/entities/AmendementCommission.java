package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class AmendementCommission {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAmandCom;
	@NotEmpty
	private String objetViser;
	@NotEmpty
	private String libelle;
	@NotEmpty
	private String contenu;
	@ManyToOne
	@JoinColumn(name="comissionPerm")
	private CommissionPermanente comissionPerm;
	@ManyToOne
	@JoinColumn(name="textLois")
	private TextLoi texteLois;
	public int getIdAmandCom() {
		return idAmandCom;
	}
	public void setIdAmandCom(int idAmandCom) {
		this.idAmandCom = idAmandCom;
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
	public CommissionPermanente getComissionPerm() {
		return comissionPerm;
	}
	public void setComissionPerm(CommissionPermanente comissionPerm) {
		this.comissionPerm = comissionPerm;
	}
	public TextLoi getTexteLois() {
		return texteLois;
	}
	public void setTexteLois(TextLoi texteLois) {
		this.texteLois = texteLois;
	}
	public AmendementCommission(@NotEmpty String objetViser, @NotEmpty String libelle, @NotEmpty String contenu,
			CommissionPermanente comissionPerm, TextLoi texteLois) {
		super();
		this.objetViser = objetViser;
		this.libelle = libelle;
		this.contenu = contenu;
		this.comissionPerm = comissionPerm;
		this.texteLois = texteLois;
	}
	public AmendementCommission() {
		super();
	}
	
	
}
