package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Entity
public class TextLoi {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLoi;
	@NotEmpty
	private String numero;
	@NotEmpty
	@Size(max=1500)
	private String libelle;
	@Size(max=1500)
	private String article1;
	@Size(max=1500)
	private String article2;
	@Size(max=1500)
	private String article3;
	@Size(max=1500)
	private String article4;
	@Size(max=1500)
	private String article5;
	private boolean status;
	private boolean active;
	@OneToMany(mappedBy="texteLois")
	private Collection<Vote> votes;
	@OneToMany(mappedBy="texteLois")
	private Collection<AmendementCommission> amendementCom;
	@OneToMany(mappedBy="texteLois")
	private Collection<AmendementDepute> amandementDep;
	@ManyToOne
	@JoinColumn(name="categories")
	private Categorie categories;
	@ManyToOne
	@JoinColumn(name="typeLois")
	private TypeLoi typeLois;
	@ManyToOne
	@JoinColumn(name="pleniers")
	private Pleniere plenieres;
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public TextLoi(@NotEmpty String numero, @NotEmpty String libelle, String article1, String article2, String article3,
			String article4, String article5, boolean status, Collection<Vote> votes,
			Collection<AmendementCommission> amendementCom, Collection<AmendementDepute> amandementDep,
			Categorie categories, TypeLoi typeLois, Pleniere plenieres) {
		super();
		this.numero = numero;
		this.libelle = libelle;
		this.article1 = article1;
		this.article2 = article2;
		this.article3 = article3;
		this.article4 = article4;
		this.article5 = article5;
		this.status = status;
		this.votes = votes;
		this.amendementCom = amendementCom;
		this.amandementDep = amandementDep;
		this.categories = categories;
		this.typeLois = typeLois;
		this.plenieres = plenieres;
	}
	public int getIdLoi() {
		return idLoi;
	}
	public void setIdLoi(int idLoi) {
		this.idLoi = idLoi;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getArticle1() {
		return article1;
	}
	public void setArticle1(String article1) {
		this.article1 = article1;
	}
	public String getArticle2() {
		return article2;
	}
	public void setArticle2(String article2) {
		this.article2 = article2;
	}
	public String getArticle3() {
		return article3;
	}
	public void setArticle3(String article3) {
		this.article3 = article3;
	}
	public String getArticle4() {
		return article4;
	}
	public void setArticle4(String article4) {
		this.article4 = article4;
	}
	public String getArticle5() {
		return article5;
	}
	public void setArticle5(String article5) {
		this.article5 = article5;
	}
	public Collection<Vote> getVotes() {
		return votes;
	}
	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
	}
	public Collection<AmendementCommission> getAmendementCom() {
		return amendementCom;
	}
	public void setAmendementCom(Collection<AmendementCommission> amendementCom) {
		this.amendementCom = amendementCom;
	}
	public Collection<AmendementDepute> getAmandementDep() {
		return amandementDep;
	}
	public void setAmandementDep(Collection<AmendementDepute> amandementDep) {
		this.amandementDep = amandementDep;
	}
	public Categorie getCategories() {
		return categories;
	}
	public void setCategories(Categorie categories) {
		this.categories = categories;
	}
	public TypeLoi getTypeLois() {
		return typeLois;
	}
	public void setTypeLois(TypeLoi typeLois) {
		this.typeLois = typeLois;
	}
	public Pleniere getPlenieres() {
		return plenieres;
	}
	public void setPlenieres(Pleniere plenieres) {
		this.plenieres = plenieres;
	}
	public TextLoi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TextLoi(String numero, String libelle, String article1, String article2, String article3, String article4,
			String article5, Categorie categories, TypeLoi typeLois, Pleniere plenieres) {
		super();
		this.numero = numero;
		this.libelle = libelle;
		this.article1 = article1;
		this.article2 = article2;
		this.article3 = article3;
		this.article4 = article4;
		this.article5 = article5;
		this.categories = categories;
		this.typeLois = typeLois;
		this.plenieres = plenieres;
	}
	
	

}
