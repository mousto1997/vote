package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Role;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.PrivilegeRepository;

@Controller
public class PrivilegeController {
	@Autowired
	private DeputeRpository deputeR;
	@Autowired
	private PrivilegeRepository privilegeR;
	@RequestMapping("/president/privilege")
	public String privilegese(Model model){
		model.addAttribute("roles", privilegeR.findAll());
		model.addAttribute("nbrP", privilegeR.count());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "privilege/privilege";
	}
	
	@RequestMapping("/president/addPrivilege")
	public String addPrivilege(Model model, String role){
		model.addAttribute("role", new Role());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "privilege/addPrivilege";
	}
	
	@RequestMapping("/president/savePrivilege")
	public String savePrivileg(Model model, @Valid Role role, BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("privileges", privilegeR.findAll());
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			return "privilege/addPrivilege";
		}
		privilegeR.save(role);
		return "redirect:/president/privilege";
	}
	@RequestMapping("/president/deletePrivilege")
	public String deletePrivileg(Model model, String role){
		privilegeR.deleteById(role);
		return "redirect:/president/privilege";
	}

}
