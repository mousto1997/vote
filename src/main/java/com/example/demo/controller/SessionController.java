package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Annee;
import com.example.demo.entities.GroupeParlementaire;
import com.example.demo.entities.Mois;
import com.example.demo.entities.Session;
import com.example.demo.entities.TypeSession;
import com.example.demo.repository.AnneeRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.MoisRepository;
import com.example.demo.repository.PleniereRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TypeSessionRepository;

@Controller
public class SessionController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public MoisRepository moi;
	@Autowired
	public AnneeRepository annee;
	@Autowired
	public TypeSessionRepository tsr;
	@Autowired
	public SessionRepository sessionRepository;
	@Autowired
	public PleniereRepository pleniereR;
	@RequestMapping(value="/depute/session", method=RequestMethod.GET)
	public String session(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<Session> sessions = sessionRepository.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("sessions",  sessions.getContent());
		int[] page = new int[ sessions.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbres",sessionRepository.count());
		model.addAttribute("mois", moi.findAll());
		model.addAttribute("annees", annee.findAll());
		model.addAttribute("typeSessions", tsr.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "session/session";
	}	
	
	@RequestMapping(value="/secretaire/addSession", method=RequestMethod.GET)
	public String addsession(Model model){
		model.addAttribute("mois", moi.findAll());
		model.addAttribute("annees", annee.findAll());
		model.addAttribute("typeSessions", tsr.findAll());
		model.addAttribute("session", new Session());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "session/addSession";
	}
	
	@RequestMapping(value="/secretaire/saveSession", method=RequestMethod.POST)
	public String saveSession(Model model,@Valid Session session, BindingResult br, String d1, String d2) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("mois", moi.findAll());
			model.addAttribute("annees", annee.findAll());
			model.addAttribute("typeSessions", tsr.findAll());
			model.addAttribute("sessions", sessionRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "session/addSession";
		}	
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		session.setDateDebut(sd.parse(d1));
		session.setDateFin(sd.parse(d2));
				
			sessionRepository.save(session);
			model.addAttribute("mois", moi.findAll());
			model.addAttribute("annees", annee.findAll());
			model.addAttribute("typeSessions", tsr.findAll());
			model.addAttribute("sessions", sessionRepository.findAll());
			model.addAttribute("session", new Session());
			return "redirect:/depute/session";		
		
	}
	

	
	private void DateTimeParser(String string) {
		// TODO Auto-generated method stub
		
	}



	@RequestMapping(value="/secretaire/editSession", method=RequestMethod.GET)
	public String editSession(Model model, int idSession){
		model.addAttribute("session", sessionRepository.getOne(idSession));		
		model.addAttribute("mois", moi.findAll());
		model.addAttribute("annees", annee.findAll());
		model.addAttribute("typeSessions", tsr.findAll());
		model.addAttribute("sessions", sessionRepository.findAll());		
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "session/editSession";
	}
	
	@RequestMapping(value="/depute/detailSession", method=RequestMethod.GET)
	public String detailSession(Model model, int idSession){
		model.addAttribute("sessions", sessionRepository.getOne(idSession));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("nombrePleniere", pleniereR.nombrePleniereBySessiion(idSession));
		return "session/detailSession";
	}
	
	@RequestMapping(value="/depute/pleniereBySession", method=RequestMethod.GET)
	public String pleniereBySession(Model model, int idSession){
		model.addAttribute("sessions", sessionRepository.getOne(idSession));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("nombrePleniere", pleniereR.nombrePleniereBySessiion(idSession));
		model.addAttribute("plenieres", pleniereR.pleniereBySessiion(idSession));
		return "pleniere/pleniereBySession";
	}
	
	@RequestMapping(value="/depute/sessions", method=RequestMethod.GET)
	public String sessions(Model model, String annee){
		model.addAttribute("sessions", sessionRepository.sessions(annee));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "session/detailSession";
	}

	
	@RequestMapping(value="/president/deleteSession", method=RequestMethod.GET)
	public String deleteSession(int idSession){
		sessionRepository.deleteById(idSession);		
		return "redirect:/depute/session";
	}
}
