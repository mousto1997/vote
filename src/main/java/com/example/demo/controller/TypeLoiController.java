package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.CommissionInfo;
import com.example.demo.entities.TypeLoi;
import com.example.demo.repository.ComissionoInfoRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TypeLoiRepository;

@Controller
public class TypeLoiController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public TypeLoiRepository tlr;
	@RequestMapping(value="/depute/typeLoi", method=RequestMethod.GET)
	public String typesession(Model model){
		model.addAttribute("typeLois", tlr.findAll());
		model.addAttribute("typeLoi", new TypeLoi());
		model.addAttribute("nbrTypeLoi", tlr.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "typeLoi/typeLoi";
	}
	
	@RequestMapping(value="/secretaire/addTypeLoi", method=RequestMethod.GET)
	public String addTypeLoi(Model model){
		model.addAttribute("typeLoi", new TypeLoi());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "typeLoi/addTypeLoi";
	}
	
	@RequestMapping(value="/secretaire/saveTypeLoi", method=RequestMethod.POST)
	public String saveTS(Model model, @Valid TypeLoi typeLoi, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("typeLois", tlr.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "typeLoi/addTypeLoi";
		}else{
			tlr.save(typeLoi);
			return "redirect:/depute/typeLoi";
		}	
	}
	
	@RequestMapping(value="/secretaire/editTypeLoi", method=RequestMethod.GET)
	public String editTS(Model model, int idType){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeLoi", tlr.getOne(idType));
		model.addAttribute("typeLois", tlr.findAll());
		return "typeLoi/editTypeLoi";
	}	
		
	@RequestMapping(value="/depute/detailTypeLoi", method=RequestMethod.GET)
	public String detailTypeLoi(Model model, int idType){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeLoi", tlr.getOne(idType));	
		return "typeLoi/detailTypeLoi";
	}
	
	@RequestMapping(value="/president/deleteTypeLoi", method=RequestMethod.GET)
	public String deleteTS(Model model, int idType){
		tlr.deleteById(idType);
		return "redirect:/depute/typeLoi";
	}
}
