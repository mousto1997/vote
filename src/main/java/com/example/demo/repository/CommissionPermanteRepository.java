package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.CommissionPermanente;
public interface CommissionPermanteRepository extends JpaRepository<CommissionPermanente, Integer> {
	
	@Query("select c from CommissionPermanente c where c.nomComPerm like :x")
	public Page<CommissionPermanente> chercher(@Param("x") String mc, Pageable  pageable);

}
