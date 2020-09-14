package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class CommissionInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idComInfo;
	@NotEmpty
	private String nomComInfo;
	private String objectif;
	@OneToMany(mappedBy="comInfo")
	private Collection<ParticipeCI> participe;
	public int getIdComInfo() {
		return idComInfo;
	}
	public void setIdComInfo(int idComInfo) {
		this.idComInfo = idComInfo;
	}
	public String getNomComInfo() {
		return nomComInfo;
	}
	public void setNomComInfo(String nomComInfo) {
		this.nomComInfo = nomComInfo;
	}
	public String getObjectif() {
		return objectif;
	}
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	public Collection<ParticipeCI> getParticipe() {
		return participe;
	}
	public void setParticipe(Collection<ParticipeCI> participe) {
		this.participe = participe;
	}
	public CommissionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommissionInfo(@NotEmpty String nomComInfo, String objectif) {
		super();
		this.nomComInfo = nomComInfo;
		this.objectif = objectif;
	}
	
	

}
