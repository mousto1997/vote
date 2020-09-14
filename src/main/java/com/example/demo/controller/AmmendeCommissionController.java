package com.example.demo.controller;

import java.text.ParseException;

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

import com.example.demo.entities.AmendementCommission;
import com.example.demo.repository.AmmendeComRepository;
import com.example.demo.repository.CommissionPermanteRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TextLoiRepository;
@Controller
public class AmmendeCommissionController {
	@Autowired
	public CommissionPermanteRepository cpr;
	@Autowired
	public TextLoiRepository tlr;
	@Autowired
	public AmmendeComRepository acr;
	@Autowired
	public DeputeRpository deputeR;
	@RequestMapping(value="/depute/ammendeCom", method=RequestMethod.GET)
	public String annee(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<AmendementCommission> amendementCommissions = acr.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("amendementCommissions", amendementCommissions.getContent());
		int[] page = new int[amendementCommissions.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrAC", acr.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "ammendeCom/ammendeCom";
	}
	
	@RequestMapping(value="/secretaire/addAmCom", method=RequestMethod.GET)
	public String addAmCom(Model model){
			model.addAttribute("commissionPermanentes", cpr.findAll());
			model.addAttribute("textLois", tlr.findAll());
			model.addAttribute("amendementCommissions", acr.findAll());
			model.addAttribute("amendementCommission", new AmendementCommission());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "ammendeCom/addAmCom";
	}
	
	
	
	@RequestMapping(value="/secretaire/saveAmmendeCom", method=RequestMethod.POST)
	public String annee(Model model,@Valid AmendementCommission amendementCommission, BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("commissionPermanentes", cpr.findAll());
			model.addAttribute("textLois", tlr.findAll());
			model.addAttribute("amendementCommissions", acr.findAll());	
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "ammendeCom/addAmCom";
		}	
		   acr.save(amendementCommission);
		   return "redirect:/depute/ammendeCom";
		}
	
	
	@RequestMapping(value="/secretaire/editAmmendeCom", method=RequestMethod.GET)
	public String editAmmendeCom(Model model, int idAmandCom){
			model.addAttribute("commissionPermanentes", cpr.findAll());
			model.addAttribute("textLois", tlr.findAll());
			model.addAttribute("amendementCommissions", acr.findAll());
			model.addAttribute("amendementCommission", acr.getOne(idAmandCom));
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "ammendeCom/editAmmendeCom";
	}

	
	@RequestMapping(value="/depute/detaitAmmendeCom", method=RequestMethod.GET)
	public String detailAmmendeCom(Model model, int idAmandCom){
			model.addAttribute("amendementCommission", acr.getOne(idAmandCom));
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "ammendeCom/detaitAmmendeCom";
	}
	
	@RequestMapping(value="/president/deleteAmmendeCom", method=RequestMethod.GET)
	public String deleteParticipe(int idAmandCom){
		acr.deleteById(idAmandCom);		
		return "redirect:/depute/ammendeCom";
	}
}
