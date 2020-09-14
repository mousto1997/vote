package com.example.demo.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.ParticipeCI;
import com.example.demo.repository.ComissionoInfoRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.ParticipeComInfoR;

@Controller
public class ParticipeComInfoController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public ComissionoInfoRepository cir;
	@Autowired
	public ParticipeComInfoR pcir;
	@RequestMapping(value="/depute/DeputeComInfo", method=RequestMethod.GET)
	public String annee(Model model){

		model.addAttribute("participes", pcir.findAll());
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("participe", new ParticipeCI());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "DeputeComInfo/DeputeComInfo";
	}
	
	@RequestMapping(value="/secretaire/addDeputeComInfo", method=RequestMethod.GET)
	public String annee(Model model, int idComInfo){
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("commissionInfos", cir.getOne(idComInfo));
		model.addAttribute("participe", new ParticipeCI());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "DeputeComInfo/addDeputeComInfo";
	}
		
	@RequestMapping(value="/secretaire/saveDeputeComInfo", method=RequestMethod.POST)
	public String annee(Model model,@Valid ParticipeCI participe, BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("participes", pcir.findAll());
			model.addAttribute("deputes", deputeR.findAll());
			model.addAttribute("commissionInfos", cir.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "DeputeComInfo/DeputeComInfo";
		}	
		pcir.save(participe);
		return "redirect:/depute/DeputeComInfo";		
	}
	
	
	@RequestMapping(value="/editDeputeComInfo", method=RequestMethod.GET)
	public String editAnnee(Model model, int idParticipe){
		  model.addAttribute("participes", pcir.findAll());
			model.addAttribute("deputes", deputeR.findAll());
			model.addAttribute("commissionInfos", cir.findAll());
			model.addAttribute("participe", pcir.getOne(idParticipe));
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "DeputeComInfo/editDeputeComInfo";
	}
	
	@RequestMapping(value="/deleteDeputeComInfo", method=RequestMethod.GET)
	public String deleteParticipe(int idParticipe){
		pcir.deleteById(idParticipe);		
		return "redirect:/DeputeComInfo";
	}
	

}
