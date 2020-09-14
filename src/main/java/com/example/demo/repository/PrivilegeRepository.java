package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Role;

public interface PrivilegeRepository extends JpaRepository<Role, String>{

}
