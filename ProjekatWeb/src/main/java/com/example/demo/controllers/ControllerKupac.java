package com.example.demo.controllers;

import com.example.demo.dto.ObilazakDTO;
import com.example.demo.exceptions.EmptyFieldsException;
import jakarta.validation.Valid;
import model.Korisnik;
import model.Obilazak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.ServiceLogin;
import com.example.demo.services.ServiceP;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/kupac/")
public class ControllerKupac {

	@Autowired
	ServiceLogin loginService;
	
	@Autowired
	ServiceP service;
	
	@GetMapping("login")
    public String login() {
        return "login";
    }
	
	@GetMapping("dodajOmiljene")
	public String dodajUOmiljene(@RequestParam("id")Integer id, Model m, HttpServletRequest request) {
		int idO = service.dodajUOmiljene(id);
		request.getSession().removeAttribute("uspesnoDodato");
		if(idO == -1) {
//			m.addAttribute("message", "Uspešno dodato u omiljene!");
			request.getSession().setAttribute("uspesnoDodato", "Došlo je do greške prilikom dodavanja u omiljene!");
		}
		else {
//			m.addAttribute("message", "Došlo je do greške prilikom dodavanja u omiljene!");
			request.getSession().setAttribute("uspesnoDodato", "Uspešno dodato u omiljene!");
		}
		return "oglas";
	}
	
	@GetMapping("getOmiljene")
	public String getOmiljene(HttpServletRequest request) {
		request.getSession().removeAttribute("omiljene");
		request.getSession().setAttribute("omiljene", service.getOmiljene());
		request.getSession().removeAttribute("uspesnoDodato");
		request.getSession().removeAttribute("uspesnoObrisana");
		return "omiljene";
	}
	
	@GetMapping("obrisiOmiljenu")
	public String obrisiOmiljenu(@RequestParam("id")Integer idN, HttpServletRequest request) {
		request.getSession().removeAttribute("uspesnoObrisana");
		boolean ok = service.deleteOmiljena(idN);
		if(ok) {
			request.getSession().setAttribute("uspesnoObrisana", "Uspešno uklonjena nekretnina!");
		}
		else {
			request.getSession().setAttribute("uspesnoObrisana", "Došlo je do greške prilikom brisanja!");
		}
		request.getSession().removeAttribute("omiljene");
		request.getSession().setAttribute("omiljene", service.getOmiljene());
		return "omiljene";
	}

	@GetMapping("zakaziObilazak")
	public String zakaziObilazak(HttpServletRequest request, Model m) {
		request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
		request.getSession().removeAttribute("messageZakazivanje");
//		request.getSession().setAttribute("messageUpeh", "Uspešno je zakazan obilazak!");
		m.addAttribute("obilazakDTO", new ObilazakDTO());
		return "oglas";
	}

	@PostMapping("/zakazi")
	public String zakazi(@Valid ObilazakDTO obilazak,
									  BindingResult bindingResult,
									  HttpServletRequest request,
									  Model m) {
		if (bindingResult.hasErrors()) {
			m.addAttribute("errors", bindingResult.getAllErrors());
			return "oglas";
		}

		Integer idN = obilazak.getNekretnina();
		int id = service.saveObilazak(obilazak, idN);

		switch (id) {
			case -1:
				m.addAttribute("messageZakazivanje", "Greška prilikom čuvanja obilaska!");
				break;
			case -2:
				m.addAttribute("messageZakazivanje", "Datum mora biti posle današnjeg!");
				break;
			case -3:
				m.addAttribute("messageZakazivanje", "Izabrana nekretnina ne postoji!");
				break;
			case -4:
				m.addAttribute("messageZakazivanje", "Korisnik ne postoji!");
				break;
			default:
				m.addAttribute("messageUspeh", "Uspešno ste zakazali obilazak!");
		}

		return "oglas";
	}

//	@GetMapping("getObilasci")
//	public String getObilasci(HttpServletRequest request) {
//		request.getSession().removeAttribute("nekretnineK");
//		request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
//		request.getSession().removeAttribute("obilasci");
//		request.getSession().removeAttribute("messageObilazak");
//		return "zakazaniObilasci";
//	}

	@GetMapping("/getObilasci")
	public String getObilasci(HttpServletRequest request) {
		request.getSession().removeAttribute("obilasci");
		request.getSession().removeAttribute("messageObilazak");
		request.getSession().removeAttribute("messageObilasci");

		List<Obilazak> obilasci = service.getObilasciKorisnika();
		request.getSession().setAttribute("obilasci", obilasci);

		if (obilasci.isEmpty()) {
			request.getSession().setAttribute("messageObilasci", "Nemate nijedan obilazak!");
		}

		return "zakazaniObilasci";
	}

	@GetMapping("ukloniObilazak")
	public String ukloniObilazak(HttpServletRequest request, @RequestParam("idO")Integer idO) {
		request.getSession().removeAttribute("messageObilazak");
		if(service.deleteObilazak(idO))
			request.getSession().setAttribute("messageObilazak", "Uspešno otkazan obilazak");
		else
			request.getSession().setAttribute("messageObilazak", "Došlo je do greške prilikom otkazivanja obilaska!");
		request.getSession().removeAttribute("obilasci");
		request.getSession().setAttribute("obilasci", service.getObilasciKorisnika());
		return "zakazaniObilasci";
	}
	
}
