package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Depute;
import com.example.demo.entities.TextLoi;
import com.example.demo.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>{
	@Query("select count(v) from Vote v where v.choix='pour' and v.texteLois.idLoi=:x ")
	public int pour(@Param("x")int idLoi);
	@Query("select count(v) from Vote v where v.choix='contre' and v.texteLois.idLoi=:x ")
	public int contre(@Param("x")int idLoi);
	@Query("select count(v) from Vote v where v.choix='abstention' and v.texteLois.idLoi=:x ")
	public int abstention(@Param("x")int idLoi);
	@Query("select count(v) from Vote v where v.texteLois.idLoi=:x ")
	public int total(@Param("x")int idLoi);
	@Query("select v from Vote v where v.texteLois.idLoi=:x and v.deputes.login=:y")
	public Vote voteByDeputeText(@Param("x")int idLoi, @Param("y")String login);
	@Query("select v from Vote v where v.deputes.login=:y")
	public Vote voteByDepute(@Param("y")String login);
	@Query("select d, v from Depute d, Vote v where d.login=v.deputes.login and v.texteLois.idLoi=:x")
	public List<Depute> deputeVotant(@Param("x")int idLoi);
	@Query("delete Vote v where v.texteLois.idLoi=:x")
	public void DeleteVoteByText(@Param("x")int idLoi);
	
}
