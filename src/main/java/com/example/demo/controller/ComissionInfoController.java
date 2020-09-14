package com.example.demo.controller;

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

import com.example.demo.entities.CommissionInfo;
import com.example.demo.repository.ComissionoInfoRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.ParticipeComInfoR;

@Controller
public class ComissionInfoController {
	@Autowired
	public ParticipeComInfoR pcir;
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public ComissionoInfoRepository cir;
	@RequestMapping(value="/depute/commissionInfo", method=RequestMethod.GET)
	public String typesession(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<CommissionInfo> comInfos = cir.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("commissionInfos", comInfos.getContent());
		int[] page = new int[comInfos.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrComInfo", cir.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "commissionInfo/commissionInfo";
	}
	
	@RequestMapping(value="/secretaire/addComInfo", method=RequestMethod.GET)
	public String addComInfo(Model model){
		model.addAttribute("commissionInfo", new CommissionInfo());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "commissionInfo/addComInfo";
	}
	
	@RequestMapping(value="/secretaire/saveCommissionInfo", method=RequestMethod.POST)
	public String saveTS(Model model, @Valid CommissionInfo commissionInfo, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("commissionInfos", cir.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "commissionInfo/addComInfo";
		}
		cir.save(commissionInfo);
		return "redirect:/depute/commissionInfo";		
	}
	
	@RequestMapping(value="/secretaire/editComInfo", method=RequestMethod.GET)
	public String editComInfo(Model model, int idComInfo){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("commissionInfo", cir.getOne(idComInfo));
		model.addAttribute("commissionInfos", cir.findAll());
		return "commissionInfo/editCommissionInfo";
	}
	
	@RequestMapping(value="/president/deleteComInfo", method=RequestMethod.GET)
	public String deleteTS(Model model, int idComInfo){
		cir.deleteById(idComInfo);
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "redirect:/depute/commissionInfo";
	}
	
	@RequestMapping(value="/depute/detailComInfo", method=RequestMethod.GET)
	public String detailCommissionInfo(Model model, int idComInfo){
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("commissionInfo", cir.getOne(idComInfo));
		model.addAttribute("membres", pcir.membresComInfo(idComInfo));
		return "commissionInfo/detailComInfo";
	}
}
