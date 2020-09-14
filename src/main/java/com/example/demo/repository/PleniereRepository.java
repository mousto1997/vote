package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Pleniere;
import com.example.demo.entities.Session;

public interface PleniereRepository extends JpaRepository<Pleniere, Integer>{
	@Query("select p from Pleniere p where p.datePleniere = :x")
	public Page<Pleniere> cherche(@Param("x")Date mc, Pageable page);
	
	@Query("select count(p) from Pleniere p where p.sessions.idSession = :x")
	public int nombrePleniereBySessiion(@Param("x")int idSession);
	
	@Query("select p from Pleniere p where p.sessions.idSession = :x")
	public List<Pleniere> pleniereBySessiion(@Param("x")int idSession);
}
