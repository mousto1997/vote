package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.assertj.core.internal.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Mois;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.MoisRepository;

@Controller
public class MoisController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public MoisRepository moiRepository;
	@RequestMapping(value="/depute/moi", method=RequestMethod.GET)
	public String mois(Model model){
		
		model.addAttribute("moi", moiRepository.findAll());
		model.addAttribute("mois", new Mois());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "mois/mois";
	}
	
	@RequestMapping(value="/secretaire/addMoi", method=RequestMethod.GET)
	public String addMoi(Model model){
		model.addAttribute("mois", new Mois());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "mois/addMoi";
	}
	
	@RequestMapping(value="/secretaire/saveMoi", method=RequestMethod.POST)
	public String saveMois(Model model, @Valid Mois mois, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("moi", moiRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "mois/mois";
		} else {
			int numMoi = mois.getMoi();
			String nomMoi="";
			if(numMoi==1){
				nomMoi="Janvier";
			}else if(numMoi==2){
				nomMoi="Fevrier";
			}else if(numMoi==3){
				nomMoi="Mars";
			}else if(numMoi==4){
				nomMoi="Avril";
			}else if(numMoi==5){
				nomMoi="Mai";
			}else if(numMoi==6){
				nomMoi="Juin";
			}else if(numMoi==7){
				nomMoi="Juillet";
			}else if(numMoi==8){
				nomMoi="Aoute";
			}else if(numMoi==9){
				nomMoi="Septembre";
			}else if(numMoi==10){
				nomMoi="Octombre";
			}else if(numMoi==11){
				nomMoi="Novembre";
			}else{
				nomMoi="Décembre";
			}
			mois.setNomMoi(nomMoi);
			moiRepository.save(mois);
			List<Mois> moi = moiRepository.findAll();
			model.addAttribute("moi", moi);
			model.addAttribute("mois", new Mois());
			
			return "redirect:/depute/mois";
		}
		
	}
	
	@RequestMapping(value="/secretaire/editMois", method=RequestMethod.GET)
	public String editAnnee(Model model, int idMois){
		model.addAttribute("mois", moiRepository.getOne(idMois));
		model.addAttribute("moi", moiRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "mois/editMois";
	}
	@RequestMapping(value="/depute/detailMois", method=RequestMethod.GET)
	public String detailMois(Model model, int idMois){
		model.addAttribute("mois", moiRepository.getOne(idMois));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "mois/detailMois";
	}
	
	@RequestMapping(value="/president/deleteMois", method=RequestMethod.GET)
	public String deleteAnnee(int idMois){
		moiRepository.deleteById(idMois);		
		return "redirect:/depute/moi";
	}

}
