package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AmendementCommission;

public interface AmmendeComRepository extends JpaRepository<AmendementCommission, Integer>{
	@Query("select a from AmendementCommission a where a.libelle like :x or a.objetViser like :x")
	public Page<AmendementCommission> cherche(@Param("x")String mc, Pageable page);
}
