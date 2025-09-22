package com.example.demo.controllers;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.NekretninaDTO;
import com.example.demo.services.ServiceLogin;
import com.example.demo.services.ServiceP;

import jakarta.servlet.http.HttpServletRequest;
import model.Nekretnina;

@Controller
@RequestMapping("/controllerP/")
public class ControllerProjekat {

	@Autowired
	ServiceP service;

	@Autowired
	ServiceLogin loginService;

	@GetMapping("login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "login";
	}

	@GetMapping("gost")
	public String gost(HttpServletRequest request, Model m) {
		List<Nekretnina> nekretnine = service.getNekretnine();
		for (Nekretnina nekretnina : nekretnine) {
			Hibernate.initialize(nekretnina.getSlikas());
		}
		request.getSession().removeAttribute("nekretnine");
		request.getSession().setAttribute("nekretnine", nekretnine);
//		request.getSession().setAttribute("tipovi", service.getTipovi());
//		request.getSession().setAttribute("gradovi", service.getGradovi());
//		m.addAttribute("tipovi", service.getTipovi());
//		m.addAttribute("gradovi", service.getGradovi());
//		m.addAttribute("searchForm", new ArrayList<Nekretnina>());
		return "glavna";
	}

	@GetMapping("glavna")
	public String glavna(HttpServletRequest request, Model m) {
		List<Nekretnina> nekretnine = service.getNekretnine();
		for (Nekretnina nekretnina : nekretnine) {
			Hibernate.initialize(nekretnina.getSlikas());
		}
		request.getSession().removeAttribute("nekretnine");
		request.getSession().setAttribute("nekretnine", nekretnine);
//		request.getSession().setAttribute("tipovi", service.getTipovi());
//		request.getSession().setAttribute("gradovi", service.getGradovi());
//		m.addAttribute("tipovi", service.getTipovi());
//		m.addAttribute("gradovi", service.getGradovi());
		return "glavna";
	}

	@GetMapping("oglas")
	public String oglas(@RequestParam("id") Integer idN, HttpServletRequest request) {
		request.getSession().setAttribute("nekretnina", service.getNekretnina(idN));
		request.getSession().setAttribute("slike", service.getSlike(idN));
		request.getSession().removeAttribute("uspesnoDodato");
		return "oglas";
	}

	@GetMapping("goBack")
	public String goBack(HttpServletRequest request) {
		List<Nekretnina> nekretnine = service.getNekretnine();
		for (Nekretnina nekretnina : nekretnine) {
			Hibernate.initialize(nekretnina.getSlikas());
		}
		request.getSession().removeAttribute("nekretnine");
		request.getSession().setAttribute("nekretnine", nekretnine);
		return "glavna";
	}
	
	@GetMapping("filteri")
	public String pretraga(HttpServletRequest request, Model m) {
		request.getSession().setAttribute("tipovi", service.getTipovi());
		request.getSession().setAttribute("gradovi", service.getGradovi());
		request.getSession().removeAttribute("searchResults");
		m.addAttribute("searchForm", new NekretninaDTO());
		return "pretraga";
	}

	@PostMapping("pretraga")
	public String search(HttpServletRequest request, @ModelAttribute("searchForm") NekretninaDTO searchForm, @RequestParam(name = "tipNekretnine", required = false) String tipNekretnine,
            @RequestParam(name = "grad", required = false) String grad,
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(name = "minSize", required = false) Integer minSize,
            @RequestParam(name = "maxSize", required = false) Integer maxSize,
            @RequestParam(name = "minBedrooms", required = false) Integer minBedrooms,
            @RequestParam(name = "minBathrooms", required = false) Integer minBathrooms) {
//		System.out.println("Krenuo controller....");
		request.getSession().removeAttribute("searchResults");
		request.getSession().removeAttribute("porukaRezultati");
//		System.out.println("Poziva se servis metod....");
		List<Nekretnina> searchResults = service.searchNekretnina(searchForm.getTip(), searchForm.getGrad(), searchForm.getMinPrice(), searchForm.getMaxPrice(), searchForm.getMinSize(), searchForm.getMaxSize(), searchForm.getMinBedrooms(), searchForm.getMinBathrooms());
		if(searchResults == null || searchResults.size() == 0) {
			request.setAttribute("porukaRezultati", "Nema nekretnina po izabranim kriterijumima!");
		}
		request.getSession().setAttribute("tipovi", service.getTipovi());
		request.getSession().setAttribute("gradovi", service.getGradovi());
		request.getSession().setAttribute("searchResults", searchResults);
		return "pretraga";
	}

}
