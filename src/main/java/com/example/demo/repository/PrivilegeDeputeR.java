package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Depute;
import com.example.demo.entities.Privillege_Depute;


public interface PrivilegeDeputeR extends JpaRepository<Privillege_Depute, Integer> {

//	@Query("delete p privilegeDepute p where p.privilege.nomPrivilege=:x and p.depute.email=:y")
//	public PrivillegeDepute deletePrivilegeDepute(@Param("x") Privilege p, @Param("y") Depute d);
}
