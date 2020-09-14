package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Privillege_Depute;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.PrivilegeDeputeR;
import com.example.demo.repository.PrivilegeRepository;

@Controller
public class PrivilegeDeputeController {
	
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public PrivilegeRepository privilegeR;
	@Autowired
	public PrivilegeDeputeR pdr;
	
	@RequestMapping(value="/president/privilegeDepute", method=RequestMethod.GET)
	public String PD(Model model){
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("privileges", privilegeR.findAll());
		model.addAttribute("privillegeDeputes", pdr.findAll());
		model.addAttribute("privillegeDepute", new Privillege_Depute());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "privilegeDepute/privilegeDepute";
	}
	
	@RequestMapping(value="/president/addPrivilegeDepute", method=RequestMethod.GET)
	public String addPrivilegeDepute(Model model){
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("roles", privilegeR.findAll());
		model.addAttribute("privillegeDeputes", pdr.findAll());
		model.addAttribute("privillegeDepute", new Privillege_Depute());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "privilegeDepute/addPrivilegeDepute";
	}
	
	@RequestMapping(value="/president/savePrivilegeDepute", method=RequestMethod.POST)
	public String savePD(Model model, @Valid Privillege_Depute privillegeDepute, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("deputes", deputeR.findAll());
			model.addAttribute("privileges", privilegeR.findAll());
			model.addAttribute("privillegeDeputes", pdr.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "privilegeDepute/privilegeDepute";
		}else{
			pdr.save(privillegeDepute);			
			return "redirect:/president/privilegeDepute";
		}	
	}
	
	@RequestMapping(value="/president/deletePrivilegeDepute", method=RequestMethod.GET)
	public String DPD(Model model, int idPD){
		pdr.deleteById(idPD);
		return "redirect:/president/privilegeDepute";
	}

}
