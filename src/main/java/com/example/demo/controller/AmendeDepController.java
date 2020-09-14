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
import com.example.demo.entities.AmendementDepute;
import com.example.demo.repository.AmendeDepRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TextLoiRepository;

@Controller
public class AmendeDepController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public TextLoiRepository tlr;
	@Autowired
	public AmendeDepRepository adr;
	@RequestMapping(value="/depute/ammendeDep", method=RequestMethod.GET)
	public String annee(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<AmendementDepute> amendementDeputes = adr.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("amendementDeputes", amendementDeputes.getContent());
		int[] page = new int[amendementDeputes.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrAD", adr.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "ammendeDep/ammendeDep";
	}
	
	@RequestMapping(value="/secretaire/addAmmendDep", method=RequestMethod.GET)
	public String addAmmendDep(Model model){
		model.addAttribute("amendementDepute", new AmendementDepute());
		model.addAttribute("textLois", tlr.findAll());
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "ammendeDep/addAmmendDep";
	}
	
	@RequestMapping(value="/secretaire/saveDmmendeDep", method=RequestMethod.POST)
	public String annee(Model model,@Valid AmendementDepute amendementDepute, BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("textLois", tlr.findAll());
			model.addAttribute("deputes", deputeR.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "ammendeDep/addAmmendDep";
			}	
		   adr.save(amendementDepute);
			return "redirect:/depute/ammendeDep";
		
	}	
	
	@RequestMapping(value="/secretaire/editAmmendDep", method=RequestMethod.GET)
	public String editAmmendeCom(Model model, int idAmandDepute){
		model.addAttribute("amendementDeputes", adr.findAll());
		model.addAttribute("textLois", tlr.findAll());
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("amendementDepute", adr.getOne(idAmandDepute));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "ammendeDep/editAmmendeDep";
	}
	
	@RequestMapping(value="/president/deleteAmmendDep", method=RequestMethod.GET)
	public String deleteParticipe(int idAmandDepute){
		adr.deleteById(idAmandDepute);		
		return "redirect:/ammendeDep";
	}
	
	@RequestMapping(value="/depute/detailAmmendeDep", method=RequestMethod.GET)
	public String detailAmmendDep(Model model, int idAmandDepute){
		model.addAttribute("textLois", tlr.findAll());
		model.addAttribute("deputes", deputeR.findAll());
		model.addAttribute("amendementDepute", adr.getOne(idAmandDepute));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "ammendeDep/detailAmmendeDep";
	}
}
