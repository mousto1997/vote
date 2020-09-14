package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Integer>{
	@Query("select s from Session s where s.nomSession like :x or s.annee.annee like :x or s.mois.nomMoi like :x or s.typeSession.libelle like :x")
	public Page<Session> cherche(@Param("x")String mc, Pageable page);
	
	@Query("select s from Session s where s.annee.annee = :x")
	public List<Session> sessions(@Param("x")String mc);
}
