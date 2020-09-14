package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idImage;
	private String nomPhoto;
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public String getNomPhoto() {
		return nomPhoto;
	}
	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}
	public Image(String nomPhoto) {
		super();
		this.nomPhoto = nomPhoto;
	}
	public Image() {
		super();
	}
	
	
}
