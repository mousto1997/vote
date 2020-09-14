package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class ParticipeCI {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idParticipe;
	@NotEmpty
	private String post;
	@ManyToOne
	@JoinColumn(name="deputes")
	private Depute deputes;
	@ManyToOne
	@JoinColumn(name="comInfo")
	private CommissionInfo comInfo;
	public int getIdParticipe() {
		return idParticipe;
	}
	public void setIdParticipe(int idParticipe) {
		this.idParticipe = idParticipe;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Depute getDeputes() {
		return deputes;
	}
	public void setDeputes(Depute deputes) {
		this.deputes = deputes;
	}
	public CommissionInfo getComInfo() {
		return comInfo;
	}
	public void setComInfo(CommissionInfo comInfo) {
		this.comInfo = comInfo;
	}
	public ParticipeCI(@NotEmpty String post, Depute deputes, CommissionInfo comInfo) {
		super();
		this.post = post;
		this.deputes = deputes;
		this.comInfo = comInfo;
	}
	public ParticipeCI() {
		super();;
		// TODO Auto-generated constructor stub
	}
	

}
