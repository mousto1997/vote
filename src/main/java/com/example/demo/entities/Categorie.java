package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class Categorie {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCat;
	@NotEmpty
	private String libell;
	@OneToMany(mappedBy="categories")
	private Collection<TextLoi> texteLois;
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getLibell() {
		return libell;
	}
	public void setLibell(String libell) {
		this.libell = libell;
	}
	public Collection<TextLoi> getTexteLois() {
		return texteLois;
	}
	public void setTexteLois(Collection<TextLoi> texteLois) {
		this.texteLois = texteLois;
	}
	public Categorie(@NotEmpty String libell) {
		super();
		this.libell = libell;
	}
	public Categorie() {
		super();
	}
	
	

}
