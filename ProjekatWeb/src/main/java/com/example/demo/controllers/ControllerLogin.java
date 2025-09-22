package com.example.demo.controllers;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.KorisnikValidation;
import com.example.demo.dto.KorisnikValidation;
import com.example.demo.services.ServiceLogin;
import com.example.demo.services.ServiceP;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import model.Korisnik;
import model.Nekretnina;

@Controller
@RequestMapping("/contrLogin/")
public class ControllerLogin {
	
	@Autowired
	ServiceLogin loginService;
	
	@Autowired
	ServiceP service;
	
	@GetMapping("login")
    public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "login";
    }
	
	@GetMapping("glavna")
	public String glavna(HttpServletRequest request) {
		List<Nekretnina> nekretnine = service.getNekretnine();
		for (Nekretnina nekretnina : nekretnine) {
			Hibernate.initialize(nekretnina.getSlikas());
		}
		request.getSession().removeAttribute("nekretnine");
		request.getSession().setAttribute("nekretnine", nekretnine);
		return "glavna";
	}
	
	@GetMapping("gost")
	public String gost(HttpServletRequest request) {
		List<Nekretnina> nekretnine = service.getNekretnine();
		for (Nekretnina nekretnina : nekretnine) {
			Hibernate.initialize(nekretnina.getSlikas());
		}
		request.getSession().removeAttribute("nekretnine");
		request.getSession().setAttribute("nekretnine", nekretnine);
		return "glavna";
	}
	
	@GetMapping("zabranjenPristup")
	public String zabranjenPristup() {
		return "zabranjenPristup";
	}
	
	@GetMapping("registracija")
	public String registracija(Model m) {
//		Korisnik k = new Korisnik();
		KorisnikValidation kv = new KorisnikValidation();
		m.addAttribute("korisnik", kv);
		m.addAttribute("uloge", loginService.getUloge());
		return "registracija";
	}
	
	@PostMapping("sacuvajKorisnika")
	public String registrujSe(@Valid @ModelAttribute("korisnik") KorisnikValidation korisnik, BindingResult bindingResult, Model m) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("uloge", loginService.getUloge());
			return "registracija";
		}
		loginService.sacuvajKorisnika(korisnik);
		return "login";
	}
	
	@GetMapping("logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "index";
    }

	@GetMapping("login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}
