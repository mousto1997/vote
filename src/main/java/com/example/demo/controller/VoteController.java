package com.example.demo.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.TextLoi;
import com.example.demo.entities.Vote;
import com.example.demo.repository.DeputeRpository;
import com.example.demo.repository.TextLoiRepository;
import com.example.demo.repository.VoteRepository;


@Controller
public class VoteController {
	@Autowired
	public DeputeRpository deputeR;
	@Autowired
	public TextLoiRepository tlr;
	@Autowired
	public VoteRepository voteRepository;
	@RequestMapping(value="/depute/vote", method=RequestMethod.GET)
	public String vote(Model model)throws ParseException{
		int nla = tlr.nla();
		if(nla == 0){
			return "redirect:/president/addVote";
		}
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("text", tlr.loiActive());
			return "vote/vote";
	}
	
	@RequestMapping(value="/president/addVote", method=RequestMethod.GET)
	public String addVote(Model model){
		int nla = tlr.nla();
		if(nla == 1){
			return "vote/nonAddVote";
		}
		model.addAttribute("texts", tlr.loiNonVote());	
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "vote/addVote";
	}
	
	@RequestMapping(value="/president/creerVote", method=RequestMethod.GET)
	public String creerVote(Model model, int idLoi){
		model.addAttribute("textLoi", tlr.getOne(idLoi));	
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		return "vote/creerVote";
	}
	
	@RequestMapping(value="/depute/saveVote", method=RequestMethod.POST)
	public String saveVote(Model model,@Valid Vote vote, TextLoi textLoi, BindingResult br) throws ParseException{
		Vote existVote = voteRepository.voteByDeputeText(vote.getTexteLois().getIdLoi(), vote.getDeputes().getLogin());
		System.out.println(vote.getTexteLois().getIdLoi()+" / "+vote.getDeputes().getLogin());
		if (existVote != null) {
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("failedVote", "Désolé. vous avez déja voté!");
			return "index";
        }
		if(br.hasErrors()){
			model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
			model.addAttribute("text", tlr.loiActive());
			return "vote/vote";
		}	
		vote.setDateVote(new Date());
		voteRepository.save(vote);
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("confirmVote", "Votre vote est pris en compte !\n merci!");
		model.addAttribute("text", tlr.loiActive());
		return "index";
	}
	
	@RequestMapping(value="depute/resultat", method=RequestMethod.GET)
	public String resultat(Model model, int idLoi){
		int pour = voteRepository.pour(idLoi);
		int contre = voteRepository.contre(idLoi);
		int absentention = voteRepository.abstention(idLoi);
		int total = voteRepository.total(idLoi);
		int pp = (pour*100)/total;
		int pc = (contre*100)/total;
		int pa = (absentention*100)/total;
		model.addAttribute("total", voteRepository.total(idLoi));
		model.addAttribute("pour", voteRepository.pour(idLoi));
		model.addAttribute("contre", voteRepository.contre(idLoi));
		model.addAttribute("abstention", voteRepository.abstention(idLoi));
		model.addAttribute("pp", pp);
		model.addAttribute("pc", pc);
		model.addAttribute("pa", pa);
		model.addAttribute("textLoi", tlr.getOne(idLoi));
		model.addAttribute("vote", tlr.loiActive());
		model.addAttribute("deputec", deputeR.deputeConnect(new HomeController().curentUser()));
		model.addAttribute("deputes", voteRepository.deputeVotant(idLoi));
		return "vote/resultat";
	}
	
	@RequestMapping(value="president/fermeture", method=RequestMethod.GET)
	public String deleteParticipe(@RequestParam(name="idLoi") int idLoi){
		tlr.Vote(idLoi);
		return "redirect:/depute/resultat";
	}

}
