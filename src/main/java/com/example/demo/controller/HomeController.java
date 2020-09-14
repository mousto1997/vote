package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Depute;
import com.example.demo.entities.Vote;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TextLoiRepository;
import com.example.demo.repository.VoteRepository;

@Controller
public class HomeController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public TextLoiRepository tlr;
	@Autowired
	public VoteRepository voteR;
	public String curentUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return name;
	}
	
	@RequestMapping(value="/depute/index", method=RequestMethod.GET)
	public String home(Model model){
		model.addAttribute("deputec", deputeR.deputeConnect(curentUser()));
	//	Depute depute = (Depute) SecurityContextHolder.getContext().getAuthentication().getDetails();
		String post = deputeR.post(curentUser());
		int ntl = tlr.nla();
		String msg;
		if(ntl!=0){
			Vote vote = voteR.voteByDeputeText(tlr.idLoiActive(), curentUser()) ;
			if(vote != null){
				model.addAttribute("loiActive", tlr.loiActive());
			}else{
				model.addAttribute("msg", "VOTER");	
			}
			model.addAttribute("textLoi", tlr.loiActive());
			model.addAttribute("desactiveVote", "Suspendre le vote");
		}else{
			if(post.equals("president")){
				model.addAttribute("msg", "ACTIVER UN VOTE");
			}else{
				model.addAttribute("msg", "AUCUN VOTE N'EST ACTIF");
			}
			System.out.println(post);
			System.out.println(curentUser());
			
		}
		return "/index";
	}
	
	@RequestMapping(value={"/index","/", "/accueil"}, method=RequestMethod.GET)
	public String logint(Model model){
		
		return "redirect:/depute/index";
	}
		
	@RequestMapping("/login")
	public String login(Model model){
		
		return "/login";
	}
	
	@RequestMapping("/403")
	public String errer(Model model){
		model.addAttribute("deputec", deputeR.deputeConnect(curentUser()));
		model.addAttribute("error", "VOUS N'AVEZ PAS D'ACCES A CETTE RESSOURCE !");
		return "/index";
	}
	
	@RequestMapping("/500")
	public String error(Model model){
		return "/error";
	}
}
