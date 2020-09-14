package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.GroupeParlementaire;

public interface GroupeParlementaireRepository extends JpaRepository<GroupeParlementaire, Integer> {
	@Query("select g from GroupeParlementaire g where g.nomGroupe like :x")
	public Page<GroupeParlementaire> cherche(@Param("x")String mc, Pageable page);
}
