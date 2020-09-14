package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.CommissionInfo;

public interface ComissionoInfoRepository extends JpaRepository<CommissionInfo, Integer> {
	@Query("select c from CommissionInfo c where c.nomComInfo like :x")
	public Page<CommissionInfo> cherche(@Param("x")String mc, Pageable page);

}
