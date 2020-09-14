package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
	@Query("select c from Categorie c where c.libell like :x")
	public Page<Categorie> cherche(@Param("x")String mc, Pageable page);
}
