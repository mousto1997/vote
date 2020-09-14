package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.TypeSession;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TypeSessionRepository;

@Controller
public class TypeSessionController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public TypeSessionRepository tsr;
	@RequestMapping(value="/depute/typeSession", method=RequestMethod.GET)
	public String typesession(Model model){
		model.addAttribute("donnee", tsr.findAll());
		model.addAttribute("typeSession", new TypeSession());
		model.addAttribute("nbrTS", tsr.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "typeSession/typeSession";
	}
	
	@RequestMapping(value="/secretaire/addTS", method=RequestMethod.GET)
	public String addTS(Model model){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeSession", new TypeSession());
		return "typeSession/addTS";
	}
	
	@RequestMapping(value="/secretaire/saveTS", method=RequestMethod.POST)
	public String saveTS(Model model, @Valid TypeSession typeSession, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("donnee", tsr.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "typeSession/addTS";
		}else{
			tsr.save(typeSession);			
			return "redirect:/depute/typeSession";
		}	
	}
	
	@RequestMapping(value="/depute/detailTS", method=RequestMethod.GET)
	public String detailTS(Model model, int idTypeSession){
		model.addAttribute("typeSession", tsr.getOne(idTypeSession));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "typeSession/detailTS";
	}
	
	@RequestMapping(value="/secretaire/editTS", method=RequestMethod.GET)
	public String editTS(Model model, int idTypeSession){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeSession", tsr.getOne(idTypeSession));
		model.addAttribute("donnee", tsr.findAll());
		return "typeSession/typeSessionEdit";
	}
	
	@RequestMapping(value="/president/deleteTS", method=RequestMethod.GET)
	public String deleteTS(Model model, int idTypeSession){
		tsr.deleteById(idTypeSession);
		return "redirect:/depute/typeSession";
	}
}
