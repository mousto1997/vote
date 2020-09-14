package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
@Entity

public class GroupeParlementaire {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGroupe;
	@NotEmpty
	private String nomGroupe;
	private String fondement;
	@OneToMany(mappedBy="groupe", cascade={CascadeType.REMOVE})
	private Collection<Depute> deputes;
	public Collection<Depute> getDeputes() {
		return deputes;
	}
	public void setDeputes(Collection<Depute> deputes) {
		this.deputes = deputes;
	}
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public String getFondement() {
		return fondement;
	}
	public void setFondement(String fondement) {
		this.fondement = fondement;
	}
	public GroupeParlementaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupeParlementaire(@NotEmpty String nomGroupe, String fondement) {
		super();
		this.nomGroupe = nomGroupe;
		this.fondement = fondement;
	}
	

}
