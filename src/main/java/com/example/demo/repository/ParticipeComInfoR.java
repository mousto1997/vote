package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.ParticipeCI;

public interface ParticipeComInfoR extends JpaRepository<ParticipeCI, Integer>{
	@Query("select p from ParticipeCI p where p.comInfo.idComInfo = :x")
	public List<ParticipeCI> membresComInfo(@Param("x")int idComInfo );

}
