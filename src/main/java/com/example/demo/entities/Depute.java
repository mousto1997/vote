package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
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
public class Depute implements Serializable {
	@Id
	@NotEmpty
	private String login;
	@NotEmpty
	private String nomDepute;
	@NotEmpty
	private String prenom;
	private String email;
	private Date dateNaiss;
	private String lieuNaiss;
	private String profession;
	private String addresse;
	private String post;
	@NotEmpty
	private String partiePolique;
	private String formation;
	private String diplome;
	private String positionAuPartiePolique;
	private String tel;
	private String photo;
	private String liste;
	@Size(max=1500)
	private String visionPolitique;
	private boolean active;
	private String pass;
	@ManyToOne
	@JoinColumn(name="groupe")
	private GroupeParlementaire groupe;
	@ManyToOne
	@JoinColumn(name="commission")
	private CommissionPermanente commission;
	@OneToMany(mappedBy="deputes", cascade={CascadeType.REMOVE})
	private Collection<Vote> votes;
	@OneToMany(mappedBy="deputes", cascade={CascadeType.REMOVE})
	private Collection<ParticipeCI>participes;
	@OneToMany(mappedBy="login")
	private Collection<Privillege_Depute> privilegeDeputes;
	@OneToMany(mappedBy="deputes", cascade={CascadeType.REMOVE})
	private Collection<AmendementDepute>amandeDep;
	public Depute(@NotEmpty String login, @NotEmpty String nomDepute, @NotEmpty String prenom, String email,
			Date dateNaiss, String lieuNaiss, String profession, String addresse, String post,
			@NotEmpty String partiePolique, String formation, String diplome, String positionAuPartiePolique,
			String tel, String photo, String liste, String visionPolitique, boolean active, String pass,
			GroupeParlementaire groupe, CommissionPermanente commission) {
		super();
		this.login = login;
		this.nomDepute = nomDepute;
		this.prenom = prenom;
		this.email = email;
		this.dateNaiss = dateNaiss;
		this.lieuNaiss = lieuNaiss;
		this.profession = profession;
		this.addresse = addresse;
		this.post = post;
		this.partiePolique = partiePolique;
		this.formation = formation;
		this.diplome = diplome;
		this.positionAuPartiePolique = positionAuPartiePolique;
		this.tel = tel;
		this.photo = photo;
		this.liste = liste;
		this.visionPolitique = visionPolitique;
		this.active = active;
		this.pass = pass;
		this.groupe = groupe;
		this.commission = commission;
	}
	public Depute() {
		super();
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomDepute() {
		return nomDepute;
	}
	public void setNomDepute(String nomDepute) {
		this.nomDepute = nomDepute;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public String getLieuNaiss() {
		return lieuNaiss;
	}
	public void setLieuNaiss(String lieuNaiss) {
		this.lieuNaiss = lieuNaiss;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPartiePolique() {
		return partiePolique;
	}
	public void setPartiePolique(String partiePolique) {
		this.partiePolique = partiePolique;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public String getPositionAuPartiePolique() {
		return positionAuPartiePolique;
	}
	public void setPositionAuPartiePolique(String positionAuPartiePolique) {
		this.positionAuPartiePolique = positionAuPartiePolique;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getListe() {
		return liste;
	}
	public void setListe(String liste) {
		this.liste = liste;
	}
	public String getVisionPolitique() {
		return visionPolitique;
	}
	public void setVisionPolitique(String visionPolitique) {
		this.visionPolitique = visionPolitique;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public GroupeParlementaire getGroupe() {
		return groupe;
	}
	public void setGroupe(GroupeParlementaire groupe) {
		this.groupe = groupe;
	}
	public CommissionPermanente getCommission() {
		return commission;
	}
	public void setCommission(CommissionPermanente commission) {
		this.commission = commission;
	}
	public Collection<Vote> getVotes() {
		return votes;
	}
	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
	}
	public Collection<ParticipeCI> getParticipes() {
		return participes;
	}
	public void setParticipes(Collection<ParticipeCI> participes) {
		this.participes = participes;
	}
	public Collection<Privillege_Depute> getPrivilegeDeputes() {
		return privilegeDeputes;
	}
	public void setPrivilegeDeputes(Collection<Privillege_Depute> privilegeDeputes) {
		this.privilegeDeputes = privilegeDeputes;
	}
	public Collection<AmendementDepute> getAmandeDep() {
		return amandeDep;
	}
	public void setAmandeDep(Collection<AmendementDepute> amandeDep) {
		this.amandeDep = amandeDep;
	}
	public Depute(@NotEmpty String login, @NotEmpty String nomDepute, @NotEmpty String prenom, String email,
			String lieuNaiss, String profession, String addresse, String post, @NotEmpty String partiePolique,
			String formation, String diplome, String positionAuPartiePolique, String tel, String photo, String liste,
			@Size(max = 1500) String visionPolitique, boolean active, String pass) {
		super();
		this.login = login;
		this.nomDepute = nomDepute;
		this.prenom = prenom;
		this.email = email;
		this.lieuNaiss = lieuNaiss;
		this.profession = profession;
		this.addresse = addresse;
		this.post = post;
		this.partiePolique = partiePolique;
		this.formation = formation;
		this.diplome = diplome;
		this.positionAuPartiePolique = positionAuPartiePolique;
		this.tel = tel;
		this.photo = photo;
		this.liste = liste;
		this.visionPolitique = visionPolitique;
		this.active = active;
		this.pass = pass;
	}
		
	
}
