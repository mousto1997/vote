package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class TypeLoi {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idType;
	@NotEmpty
	private String libelle;
	@OneToMany(mappedBy="typeLois")
	private Collection<TextLoi> texteLois;
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<TextLoi> getTexteLois() {
		return texteLois;
	}
	public void setTexteLois(Collection<TextLoi> texteLois) {
		this.texteLois = texteLois;
	}
	public TypeLoi(@NotEmpty String libelle) {
		super();
		this.libelle = libelle;
	}
	public TypeLoi() {
		super();
	}
	
	

}
