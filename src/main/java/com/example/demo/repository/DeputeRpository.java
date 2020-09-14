package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.CommissionInfo;
import com.example.demo.entities.Depute;

public interface DeputeRpository extends JpaRepository<Depute, String> {
	@Query("select d from Depute d where d.post like :x or d.nomDepute like :x or d.prenom like :x or d.email like :x or d.login like :x")
	public Page<Depute> cherche(@Param("x")String mc, Pageable page);
	
	@Query("select d from Depute d where d.post='President' ")
	public List<Depute> president();
	
	@Query("select d from Depute d where d.groupe.idGroupe = :x ")
	public List<Depute> groupe(@Param("x")int idGroupe);
	
	@Query("select count(d) from Depute d where d.groupe.idGroupe = :x ")
	public int nbrGroupe(@Param("x")int idGroupe);
	
	@Query("select d from Depute d where d.commission.idComPerm = :x ")
	public List<Depute> deputeOfCommission(@Param("x")int idComPerm);
	
	@Query("select count(d) from Depute d where d.commission.idComPerm = :x ")
	public int nbreDeputeOfCommission(@Param("x")int idComPerm);
	
	@Query("select d from Depute d where d.post='Secretaire' ")
	public List<Depute> secretaire();
	
//	@Query("ALTER TABLE Depute d SET d.pass = :y WHERE d.login = :x")
//	public void setPass(@Param("x")String login, @Param("y")String pass);
//	
	@Query("select d from Depute d where d.login=:x")
	public Depute deputeConnect(@Param("x")String login);
	
	@Query("select d from Depute d where d.login=:x")
	public Depute findDeputeByLogin(@Param("x")String login);
	
	@Query("select d.post from Depute d where d.login=:x")
	public String post(@Param("x")String login);
	
	@Query("update Depute d set d.pass = :x, d.login = :y where d.login=:z ")
	public Depute udatePass(@Param("x")String mdp, @Param("y")String login, @Param("z")String log);
	
//	@Query("select d from Depute d where d.active=true")
//	public Depute deputeConnecte();
}
