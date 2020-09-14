package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Annee;
import com.example.demo.entities.TypeSession;
import com.example.demo.repository.AnneeRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TypeSessionRepository;

@Controller
public class AnneeController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public AnneeRepository anneeRepository;
	@RequestMapping(value="/depute/annee", method=RequestMethod.GET)
	public String annee(Model model){
		
		model.addAttribute("annees", anneeRepository.findAll());
		model.addAttribute("annee", new Annee());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "/annee/annee";
	}
	
	@RequestMapping(value="/secretaire/addAnnee", method=RequestMethod.GET)
	public String addAnnee(Model model){
		model.addAttribute("annee", new Annee());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "annee/addAnnee";
	}
	
	@RequestMapping(value="/depute/detailAnnee", method=RequestMethod.GET)
	public String listeAnnee(Model model, int idAnnee){
		
		model.addAttribute("annee", anneeRepository.getOne(idAnnee));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "/annee/detailAnnee";
	}
	
	@RequestMapping(value="/secretaire/saveAnnee", method=RequestMethod.POST)
	public String saveAnnee(Model model,@Valid Annee annee, BindingResult br ){
		if(br.hasErrors()){
			model.addAttribute("annees", anneeRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "annee/annee";
		}
		anneeRepository.save(annee);
		return "redirect:/depute/annee";
	}
	
	@RequestMapping(value="/secretaire/editAnnee", method=RequestMethod.GET)
	public String editAnnee(Model model, int idAnnee){
		model.addAttribute("annee", anneeRepository.getOne(idAnnee));
		model.addAttribute("annees", anneeRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "annee/editAnnee";
	}
	
	@RequestMapping(value="/president/deleteAnnee", method=RequestMethod.GET)
	public String deleteAnnee(int idAnnee){
		anneeRepository.deleteById(idAnnee);		
		return "redirect:/depute/annee";
	}
}
