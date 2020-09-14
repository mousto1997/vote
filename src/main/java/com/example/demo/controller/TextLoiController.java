package com.example.demo.controller;

import java.text.ParseException;
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
import com.example.demo.entities.TextLoi;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.PleniereRepository;
import com.example.demo.repository.TextLoiRepository;
import com.example.demo.repository.TypeLoiRepository;
import com.example.demo.repository.VoteRepository;

@Controller
public class TextLoiController {
	@Autowired
	public VoteRepository voteR;
	@Autowired
	public TextLoiRepository textLoiRepository;
	@Autowired
	public PleniereRepository pleniereRepository;
	@Autowired
	public CategorieRepository categorieRepository;
	@Autowired
	public TypeLoiRepository tlr;
	@Autowired
	public DeputeRpository deputeR;

	@RequestMapping(value="/depute/textLoi", method=RequestMethod.GET)
	public String typesession(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<TextLoi> textLois = textLoiRepository.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("textLois", textLois.getContent());
		int[] page = new int[textLois.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrText", textLoiRepository.count());
		model.addAttribute("plenieres", pleniereRepository.findAll());
		model.addAttribute("typeLois", tlr.findAll());
		model.addAttribute("categories", categorieRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		List<TextLoi>  t = textLoiRepository.findAll();
		return "textLoi/textLoi";
	}
	
	@RequestMapping(value="/secretaire/addTextLoi", method=RequestMethod.GET)
	public String addTextLoi(Model model){
		model.addAttribute("textLoi", new TextLoi());
		model.addAttribute("categories",categorieRepository.findAll());
		model.addAttribute("plenieres",pleniereRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeLois", tlr.findAll());
		return "textLoi/addText";
	}
	
	@RequestMapping(value="/secretaire/saveText", method=RequestMethod.POST)
	public String saveTS(Model model, @Valid TextLoi textLoi , BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("categories",categorieRepository.findAll());
			model.addAttribute("plenieres",pleniereRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("typeLois", tlr.findAll());
			return "textLoi/addText";
		}else{
			 textLoiRepository.save(textLoi);
			return "redirect:/depute/textLoi";
		}	
	}
	
	@RequestMapping(value="/president/saveText", method=RequestMethod.POST)
	public String saveText(Model model, @Valid TextLoi textLoi , BindingResult br) throws ParseException{
		if(br.hasErrors()){
			model.addAttribute("categories",categorieRepository.findAll());
			model.addAttribute("plenieres",pleniereRepository.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("typeLois", tlr.findAll());
			return "textLoi/addText";
		}
			 textLoiRepository.save(textLoi);
		//	 voteR.DeleteVoteByText(textLoi.getIdLoi());
			return "redirect:/depute/index";
	}
	
	@RequestMapping(value="/secretaire/editTextLoi", method=RequestMethod.GET)
	public String editText(Model model, int idLoi){
		model.addAttribute("textLoi", textLoiRepository.getOne(idLoi));
		model.addAttribute("textLois", textLoiRepository.findAll());
		model.addAttribute("categories",categorieRepository.findAll());
		model.addAttribute("plenieres",pleniereRepository.findAll());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("typeLois", tlr.findAll());
		return "textLoi/EditTextLoi";
	}
	
	
	@RequestMapping(value="/president/deleteTextLoi", method=RequestMethod.GET)
	public String deleteText(Model model, int idLoi){
		textLoiRepository.deleteById(idLoi);
		return "redirect:/depute/textLoi";
	}
	
	@RequestMapping(value="/depute/detailText", method=RequestMethod.GET)
	public String detailText(Model model, int idLoi){
		model.addAttribute("textLoi", textLoiRepository.getOne(idLoi));
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		String msg = "Conkry le ";
		model.addAttribute("msg", msg);
		return "textLoi/detailText";
	}

}
