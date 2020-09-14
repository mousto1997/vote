package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.TextLoi;

public interface TextLoiRepository extends JpaRepository<TextLoi, Integer> {
	@Query("select t from TextLoi t where t.numero like :x or t.libelle like :x")
	public Page<TextLoi> cherche(@Param("x")String mc, Pageable page);
	
	@Query("select t from TextLoi t where t.active=true ")
	public List<TextLoi> loisActive();
	
	@Query("select t from TextLoi t where t.status=true ")
	public List<TextLoi> loiVote();
	
	@Query("select t from TextLoi t where t.status=false ")
	public List<TextLoi> loiNonVote();
	
	@Query("select t from TextLoi t where t.plenieres.idPleniere = :x ")
	public List<TextLoi> loiByPleniere(@Param("x")int idPleniere);
	
	@Query("select count(t) from TextLoi t where t.plenieres.idPleniere = :x ")
	public int nbreLoiByPleniere(@Param("x")int idPleniere);
	
	@Query("update TextLoi t set t.status = true where t.idLoi=:x ")
	public void Vote(@Param("x")int idLoi);
	
	@Query("select count(t) from TextLoi t where t.active=true and t.status=false")
	public int nla();
	
	@Query("select t from TextLoi t where t.active=true and t.status=false")
	public TextLoi loiActive();
	
	@Query("select t.idLoi from TextLoi t where t.active=true and t.status=false")
	public int idLoiActive();
}
