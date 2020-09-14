package com.example.demo.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Annee;
import com.example.demo.entities.CommissionInfo;
import com.example.demo.entities.GroupeParlementaire;
import com.example.demo.entities.TypeSession;
import com.example.demo.repository.AnneeRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.GroupeParlementaireRepository;
import com.example.demo.repository.TypeSessionRepository;

@Controller
public class GroupeParlementaireController {
	@Autowired
	public GroupeParlementaireRepository groupeParlRepository;
	@Autowired
	public DeputeRpository deputeRepository;
	@RequestMapping(value="/depute/groupeParlementaire", method=RequestMethod.GET)
	public String typesession(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<GroupeParlementaire> groupes = groupeParlRepository.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("groupeParlementaires", groupes.getContent());
		int[] page = new int[groupes.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("nbrGr", groupeParlRepository.count());
		return "groupeParlementaire/groupeParlementaire";
	}
	
	@RequestMapping(value="/secretaire/addGroupe", method=RequestMethod.GET)
	public String addGroupe(Model model){
		
		model.addAttribute("groupeParlementaire", new GroupeParlementaire());
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		return "groupeParlementaire/addGroupe";
	}
	
	@RequestMapping(value="/secretaire/saveGroupe", method=RequestMethod.POST)
	public String saveAnnee(Model model, @Valid GroupeParlementaire groupeParlementaire ,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			model.addAttribute("groupeParlementaires",  groupeParlRepository.findAll());
			model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
			return "groupeParlementaire/addGroupe";
		}
		else{
			groupeParlRepository.save(groupeParlementaire);
			List<GroupeParlementaire> mesgrp = groupeParlRepository.findAll();
			model.addAttribute("groupeParlementaires", mesgrp);
			model.addAttribute("groupeParlementaire", new GroupeParlementaire());
			return "redirect:/depute/groupeParlementaire";
		}

	}
	
	@RequestMapping(value="/secretaire/editGroupe", method=RequestMethod.GET)
	public String editAnnee(Model model, int  idGroupe){
		
		model.addAttribute("groupeParlementaire", groupeParlRepository.getOne(idGroupe));
		model.addAttribute("groupeParlementaires",  groupeParlRepository.findAll());
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		return "groupeParlementaire/editGroupeParlementaire";
	}
	
	@RequestMapping(value="/depute/detailGroupe", method=RequestMethod.GET)
	public String detailGroupe(Model model, int  idGroupe){
		model.addAttribute("groupeParlementaire", groupeParlRepository.getOne(idGroupe));
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("deputes", deputeRepository.groupe(idGroupe));
		model.addAttribute("nbrDepute", deputeRepository.nbrGroupe(idGroupe));
//		for(int i=1; i<= deputeRepository.nbrGroupe(idGroupe); i++){
//			model.addAttribute("i", i);
//		}
		int i=1;
		model.addAttribute("i", i);
		return "groupeParlementaire/detailGroupeParlementaire";
	}
	
	@RequestMapping(value="/president/deleteGroupe", method=RequestMethod.GET)
	public String deleteAnnee(int idGroupe){
		groupeParlRepository.deleteById(idGroupe);		
		return "redirect:/depute/groupeParlementaire";
	}
	

}
