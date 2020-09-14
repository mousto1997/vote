package com.example.demo.controller;

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

import com.example.demo.entities.CommissionPermanente;
import com.example.demo.repository.CommissionPermanteRepository;
import com.example.demo.repository.DeputeRpository;


@Controller
public class CommissionPermanenteController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public CommissionPermanteRepository permanenteRepository;
	@RequestMapping(value="/depute/commissionPermanente", method=RequestMethod.GET)
	public String commissionPermanente(Model model, @RequestParam(name="page", defaultValue="0")int p,
						@RequestParam(name="size", defaultValue = "5")int s,
						@RequestParam(name="motCle", defaultValue = "")String mc){
		model.addAttribute("commissionPermanente", new CommissionPermanente());
		Page<CommissionPermanente> commissionPermanantes = permanenteRepository.chercher("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("commissionPermanentes", commissionPermanantes.getContent());
		int[] page = new int[commissionPermanantes.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);		
		model.addAttribute("nbrComPerm", permanenteRepository.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "commissionPermanente/commissionPermanente";
	}
	
	@RequestMapping(value="/secretaire/addComPerm", method=RequestMethod.GET)
	public String addComPerm(Model model){
		model.addAttribute("commissionPermanente", new CommissionPermanente());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "commissionPermanente/addComPerm";
	}
	
	@RequestMapping(value="/secretaire/savePermanente", method=RequestMethod.POST)
	public String saveperm(Model model, @Valid CommissionPermanente commissionPermanente, BindingResult br){
		if(br.hasErrors()){
			List<CommissionPermanente> mescp = permanenteRepository.findAll();
			model.addAttribute("commissionPermanentes", mescp);
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "commissionPermanente/addComPerm";
		}
			permanenteRepository.save(commissionPermanente);
			return "redirect:/depute/commissionPermanente";
			
	}
	
	@RequestMapping(value="/secretaire/editComPerm", method=RequestMethod.GET)
	public String editperm(Model model, int idComPerm){
		model.addAttribute("commissionPermanente", permanenteRepository.getOne(idComPerm));
		List<CommissionPermanente> mescp = permanenteRepository.findAll();
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "commissionPermanente/editePermanente";
	}
	
	@RequestMapping(value="/president/deleteComPerm", method=RequestMethod.GET)
	public String deleteperm(Model model, int idComPerm, @RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue = "4")int size,
			@RequestParam(name="motCle", defaultValue = "")String motCle){
			permanenteRepository.deleteById(idComPerm);
		return "redirect:/depute/commissionPermanente?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	@RequestMapping(value="/depute/detailComPerm", method=RequestMethod.GET)
	public String detailPermanente(Model model, int idComPerm){
		model.addAttribute("commissionPermanente", permanenteRepository.getOne(idComPerm));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("deputes", deputeR.deputeOfCommission(idComPerm));
		model.addAttribute("nbrDepute", deputeR.nbreDeputeOfCommission(idComPerm));
		return "commissionPermanente/detailPermanente";
	}

}
