package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class CommissionPermanente {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int idComPerm;
@NotEmpty
private String nomComPerm;
@OneToMany(mappedBy="comissionPerm")
private Collection<AmendementCommission> amandementCom;
@OneToMany(mappedBy="commission", cascade={CascadeType.REMOVE})
private Collection<Depute> deputes;
public Collection<Depute> getDeputes() {
	return deputes;
}
public void setDeputes(Collection<Depute> deputes) {
	this.deputes = deputes;
}
public int getIdComPerm() {
	return idComPerm;
}
public void setIdComPerm(int idComPerm) {
	this.idComPerm = idComPerm;
}
public String getNomComPerm() {
	return nomComPerm;
}
public void setNomComPerm(String nomComPerm) {
	this.nomComPerm = nomComPerm;
}
public Collection<AmendementCommission> getAmandementCom() {
	return amandementCom;
}
public void setAmandementCom(Collection<AmendementCommission> amandementCom) {
	this.amandementCom = amandementCom;
}
public CommissionPermanente() {
	super();
	// TODO Auto-generated constructor stub
}
public CommissionPermanente(@NotEmpty String nomComPerm) {
	super();
	this.nomComPerm = nomComPerm;
}



}
