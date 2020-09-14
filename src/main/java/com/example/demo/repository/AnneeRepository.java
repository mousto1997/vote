package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Annee;
import com.example.demo.entities.Mois;

public interface AnneeRepository extends JpaRepository<Annee, Integer>{

}
