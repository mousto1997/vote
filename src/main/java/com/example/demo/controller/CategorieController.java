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

import com.example.demo.entities.Categorie;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.DeputeRpository;

@Controller
public class CategorieController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public CategorieRepository categorieRepository;
	@RequestMapping(value="/depute/categorie", method=RequestMethod.GET)
	public String categorie(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<Categorie> categories = categorieRepository.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("categories", categories.getContent());
		int[] page = new int[categories.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrCat", categorieRepository.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "categorie/categorie";
	}
	
	@RequestMapping(value="/secretaire/addCat", method=RequestMethod.GET)
	public String addCat(Model model){
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "categorie/addCat";
	}
	
	@RequestMapping(value="/secretaire/saveCategorie", method=RequestMethod.POST)
	public String saveCat(Model model, @Valid Categorie categorie, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("categories", categorieRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "categorie/addCat";
		}
		categorieRepository.save(categorie);
		return "redirect:/depute/categorie";
	}
	
	@RequestMapping(value="/secretaire/editCat", method=RequestMethod.GET)
	public String editAnnee(Model model, int idCat){
		model.addAttribute("categorie", categorieRepository.getOne(idCat));
		model.addAttribute("categories", categorieRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "categorie/editCategorie";
	}

	@RequestMapping(value="/depute/detailCat", method=RequestMethod.GET)
	public String detailCategorie(Model model, int idCat){
		model.addAttribute("categorie", categorieRepository.getOne(idCat));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "categorie/detailCategorie";
	}
	
	
	@RequestMapping(value="/president/deleteCat", method=RequestMethod.GET)
	public String deleteAnnee(int idCat){
		categorieRepository.deleteById(idCat);		
		return "redirect:/depute/categorie";
	}
}
