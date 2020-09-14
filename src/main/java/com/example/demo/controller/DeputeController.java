package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Depute;
import com.example.demo.entities.Privillege_Depute;
import com.example.demo.repository.ComissionoInfoRepository;
import com.example.demo.repository.CommissionPermanteRepository;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.GroupeParlementaireRepository;


@Controller
public class DeputeController {
	@Autowired
	public GroupeParlementaireRepository gpr;
	@Autowired
	public CommissionPermanteRepository cpr;
	@Autowired
	public ComissionoInfoRepository cir;
	@Autowired
	public DeputeRpository deputeRpository;
	@RequestMapping(value="/depute/depute", method=RequestMethod.GET)
	public String depute(Model model, @RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue = "9")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc){
		Page<Depute> deputes = deputeRpository.cherche("%"+mc+"%", new PageRequest(p, s));
		model.addAttribute("deputes", deputes.getContent());
		int[] page = new int[deputes.getTotalPages()];
		model.addAttribute("page", page);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("nbrDepute", deputeRpository.count());
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		return "depute/depute";
	}
	
	@RequestMapping(value="/secretaire/addDepute", method=RequestMethod.GET)
	public String addDepute(Model model){
		model.addAttribute("groupes", gpr.findAll());
		model.addAttribute("commissions", cpr.findAll());
		model.addAttribute("depute", new Depute());
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		return "depute/addDepute";
	}	
	
	public String path=System.getProperty("user.dir")+"/src/main/resources/static/image";
	
	@RequestMapping(value="/secretaire/saveDepute", method=RequestMethod.POST)
	public String saveDepute(Model model, @Valid Depute depute, BindingResult br, String dt, String mdp, MultipartFile file)throws IOException, ParseException{
		Depute deput = deputeRpository.findDeputeByLogin(depute.getLogin());
			
		System.out.println("log 1 : "+depute.getLogin());
		
		if(deput != null){
			System.out.println("log 2 : "+depute.getLogin());
			if(file.isEmpty()){
				depute.setPhoto(deput.getPhoto());
			}
			if(dt.isEmpty()){
				depute.setDateNaiss(deput.getDateNaiss());
			}
			if(mdp.isEmpty()){
				depute.setPass(deput.getPass());
			}
			if(depute.getListe()==null){
				depute.setListe(deput.getListe());
			}
		}
		if(br.hasErrors()){
			model.addAttribute("groupes", gpr.findAll());
			model.addAttribute("commissions", cpr.findAll());
			model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
			return "depute/addDepute";
		}
		if(!(file.isEmpty())){
			StringBuilder fileNames=new StringBuilder();
			System.out.println(file.getOriginalFilename());
			System.out.println(path);
			file.transferTo(new File(path+"/"+"DEP_"+file.getOriginalFilename()));			
			depute.setPhoto("DEP_"+file.getOriginalFilename());
		}
		if(!dt.isEmpty()){
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			depute.setDateNaiss(sd.parse(dt));
		}
		if(!(mdp.isEmpty())){
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			depute.setPass(bc.encode(mdp));
		}
		depute.setActive(true);
			deputeRpository.save(depute);		
			return "redirect:/depute/depute";	
	}
	
//	@RequestMapping(value="/depute/saveDepute", method=RequestMethod.POST)
//	public String savePass(Model model, String mdp){
//		Depute deput = deputeRpository.getOne(new HomeController().curentUser());
//		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
//		deputeRpository.setPass(deput.getLogin(), bc.encode(mdp));
//		return "redirect:/depute/detailDepute(login=HomeController().curentUser())";
//	}
	
	@RequestMapping(value="/depute/saveDepute", method=RequestMethod.POST)
	public String savePassDepute(Model model, @Valid Depute depute, BindingResult br, String mdp)throws IOException, ParseException{
		Depute deput = deputeRpository.findDeputeByLogin(depute.getLogin());
		
		if(br.hasErrors()){
			model.addAttribute("groupes", gpr.findAll());
			model.addAttribute("commissions", cpr.findAll());
			model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
			return "depute/addDepute";
		}
		
			depute.setDateNaiss(deput.getDateNaiss());
			depute.setGroupe(deput.getGroupe());
			depute.setCommission(deput.getCommission());
			depute.setActive(true);
			depute.setPhoto(deput.getPhoto());
			depute.setListe(deput.getListe());
		
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			depute.setPass(bc.encode(mdp));
		
			deputeRpository.save(depute);
			model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("confirmSetPass", "Mot de pass modifié avec succée !");
			return "index";	
	}

	@RequestMapping(value="/secretaire/editDepute", method=RequestMethod.GET)
	public String editDepute(Model model, String login){
		model.addAttribute("groupes", gpr.findAll());
		model.addAttribute("commissions", cpr.findAll());
		model.addAttribute("depute", deputeRpository.getOne(login));
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		return "depute/editDepute";
	}
	
	@RequestMapping(value="/depute/editPass", method=RequestMethod.GET)
	public String editDepute2(Model model){
		model.addAttribute("groupes", gpr.findAll());
		model.addAttribute("commissions", cpr.findAll());
		model.addAttribute("depute", deputeRpository.getOne(new HomeController().curentUser()));
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		return "depute/editPass";
	}
	
	@RequestMapping(value="/depute/savePass", method=RequestMethod.POST)
	public String savePass(Model model, String mdp, String login, String log){
		deputeRpository.udatePass(mdp, login, log);
		return "depute/editDepute2";
	}
	
	@RequestMapping(value="/president/deleteDepute", method=RequestMethod.GET)
	public String deleteDepute(String login){
		deputeRpository.deleteById(login);		
		return "redirect:/depute/depute";
	}	
	
	@RequestMapping(value="/depute/detailDepute", method=RequestMethod.GET)
	public String detailDepute(Model model, String login){
		Depute depute = deputeRpository.getOne(login);
		model.addAttribute("depute", depute);
		model.addAttribute("nomPhoto",depute.getPhoto());
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("commissions", cpr.findAll());
		return "depute/detailDepute";
	}
	
	@RequestMapping(value="/depute/groupe", method=RequestMethod.GET)
	public String detailDepute(Model model, int idGroupe){
		model.addAttribute("deputec", deputeRpository.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("deputes", deputeRpository.groupe(idGroupe));
		model.addAttribute("commissions", cpr.findAll());
		model.addAttribute("nbrDepute", deputeRpository.nbrGroupe(idGroupe));
		return "depute/liste";
	}
	
	@RequestMapping(value="/depute/photoDepute", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photeDeputet( String email)throws IOException{
		Depute d = deputeRpository.getOne(email);
		File f=new File(path+"/"+"DEP_"+d.getPhoto());
		return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(f));
	}
}