package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Annee;
import com.example.demo.entities.Mois;
import com.example.demo.entities.Pleniere;
import com.example.demo.entities.Session;
import com.example.demo.entities.TypeSession;
import com.example.demo.repository.AnneeRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.MoisRepository;
import com.example.demo.repository.PleniereRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TextLoiRepository;
import com.example.demo.repository.TypeSessionRepository;

@Controller
public class PleniereController {
	@Autowired
	public SessionRepository sessionRepository;
	@Autowired
	public DeputeRpository deputeRepository;
	@Autowired
	public PleniereRepository pleniereRepository;
	@Autowired
	public TextLoiRepository tlr;
	@RequestMapping(value="/depute/pleniere", method=RequestMethod.GET)
	public String annee(Model model){
		model.addAttribute("plenieres", pleniereRepository.findAll());
		model.addAttribute("pleniere", new Pleniere());
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("nbrePleniere", pleniereRepository.count());
		return "pleniere/pleniere";
	}
	
	@RequestMapping(value="/secretaire/addPleniere", method=RequestMethod.GET)
	public String addPleniere(Model model){
		model.addAttribute("pleniere", new Pleniere());
		model.addAttribute("presidents", deputeRepository.president());
		model.addAttribute("secretaires", deputeRepository.secretaire());
		model.addAttribute("sessions", sessionRepository.findAll());
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		return "pleniere/addPleniere";
	}
	
	@RequestMapping(value="/secretaire/savePleniere", method=RequestMethod.POST)
	public String annee(Model model,@Valid Pleniere pleniere, BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("sessions", sessionRepository.findAll());
			model.addAttribute("plenieres", pleniereRepository.findAll());
			model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
			return "pleniere/addPleniere";
		}	
		
		pleniere.setDatePleniere(new Date());
		pleniereRepository.save(pleniere);
		return "redirect:/depute/pleniere";		
	}
	
	
	@RequestMapping(value="/secretaire/editPleniere", method=RequestMethod.GET)
	public String editPleniere(Model model, int idPleniere){
		model.addAttribute("pleniere", pleniereRepository.getOne(idPleniere));
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("sessions", sessionRepository.findAll());
		model.addAttribute("presidents", deputeRepository.president());
		model.addAttribute("secretaires", deputeRepository.secretaire());
		model.addAttribute("plenieres", pleniereRepository.findAll());
		return "pleniere/editPleniere";
	}
	
	@RequestMapping(value="/president/deletePleniere", method=RequestMethod.GET)
	public String deletePleniere(int idPleniere){
		pleniereRepository.deleteById(idPleniere);		
		return "redirect:/depute/pleniere";
	}
	
	@RequestMapping(value="/depute/detailPleniere", method=RequestMethod.GET)
	public String detailPleniere(Model model, int idPleniere){
		model.addAttribute("pleniere", pleniereRepository.getOne(idPleniere));
		String msg="Conakry le ";
		model.addAttribute("msg", msg);
		model.addAttribute("nbreLoi", tlr.nbreLoiByPleniere(idPleniere));
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		return "pleniere/detailPleniere";
	}
	
	@RequestMapping(value="/depute/loiByPleniere", method=RequestMethod.GET)
	public String loiByPleniere(Model model, int idPleniere){
		model.addAttribute("pleniere", pleniereRepository.getOne(idPleniere));
		String msg="Conakry le ";
		model.addAttribute("msg", msg);
		model.addAttribute("nbreLoi", tlr.nbreLoiByPleniere(idPleniere));
		model.addAttribute("textLois", tlr.loiByPleniere(idPleniere));
		model.addAttribute("deputec", deputeRepository.deputeConnect(new HomeController().curentUser()));
		return "textLoi/loiByPleniere";
	}

}
