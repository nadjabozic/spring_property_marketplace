package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.NekretninaDTO;
import com.example.demo.dto.ObilazakDTO;
import com.example.demo.exceptions.EmptyFieldsException;
import com.example.demo.exceptions.InvalidLengthException;
import com.example.demo.exceptions.NegativeNumberException;
import com.example.demo.exceptions.SavingErrorException;
import com.example.demo.services.ServiceLogin;
import com.example.demo.services.ServiceP;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/prodavac/")
public class ControllerProdavac {
	
	@Autowired
	ServiceLogin loginService;
	
	@Autowired
	ServiceP service;
	
	@GetMapping("login")
    public String login() {
        return "login";
    }
	
	@GetMapping("getNekretnine")
	public String getNekretnine(HttpServletRequest request) {
		request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
		request.getSession().removeAttribute("message");
		return "oglasi";
	}
	
	@GetMapping("prelazUnos")
	public String stranicaUnos(Model m, HttpServletRequest request) {
		m.addAttribute("nekretninaDTO", new NekretninaDTO());
		request.getSession().setAttribute("tipovi", service.getTipovi());
		request.getSession().setAttribute("gradovi", service.getGradovi());
		m.addAttribute("tipovi", service.getTipovi());
		m.addAttribute("gradovi", service.getGradovi());
		return "unosNekretnine";
	}
	
	@PostMapping("sacuvajNekretninu")
	public String sacuvajNekretninu(@Valid NekretninaDTO nekretnina, BindingResult bindingResult, @RequestParam("tip") String tip, @RequestParam("grad") String grad, @RequestParam("slike") MultipartFile[] slike, Model m, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			m.addAttribute("errors", bindingResult.getAllErrors());
		} else {
			if(nekretnina.getKvadratura() <= 0) {
				throw new NegativeNumberException("Kvadratura ne može biti 0 ili negativna!");			
			}
			if(nekretnina.getBrojSpavacihSoba() <= 0) {
				throw new NegativeNumberException("Broj spavaćih soba ne može biti 0 ili negativna!");
			}
			if(nekretnina.getBrojKupatila() <= 0) {
				throw new NegativeNumberException("Broj kupatila ne može biti 0 ili negativna!");
			}
			if(nekretnina.getCena() <= 0) {
				throw new NegativeNumberException("Cena ne može biti 0 ili negativna!");
			}
			if(slike == null) {
				throw new EmptyFieldsException("Morate ubaciti bar jednu sliku!");
			}
			if(nekretnina.getOpis().length() > 499) {
				throw new InvalidLengthException("Opis je predugacak", nekretnina.getOpis().length());
			}
			int id = service.saveNekretnina(nekretnina, tip, grad, slike);
			m.addAttribute("message", "");
			if (id == -1) {
				throw new SavingErrorException("Greška prilikom čuvanja nekretnine!");
//				m.addAttribute("message", "Greška prilikom čuvanja nekretnine!");
			} else {
				m.addAttribute("nekretninaDTO", new NekretninaDTO());
				m.addAttribute("message", "Uspešno je sačuvana nekretnina!");
			}
		}
		return "unosNekretnine";
	}
	
	@GetMapping("oglas")
	public String oglas(@RequestParam("id")Integer idN, HttpServletRequest request) {
		request.getSession().setAttribute("nekretnina", service.getNekretnina(idN));
		request.getSession().setAttribute("slike", service.getSlike(idN));
		return "oglas";
	}
	
	@GetMapping("ukloniOglas")
	public String ukloniOglas(@RequestParam("id") Integer idN, HttpServletRequest request) {
		boolean success = service.deleteNekretnina(idN);
		if(success) {
			request.getSession().setAttribute("message", "Uspešno obrisan oglas");
			request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
		}
		else {
			request.getSession().setAttribute("message", "Došlo je do greške prilikom brisanja!");
		}
		return "oglasi";
	}
	
//	@GetMapping("zakaziObilazak")
//	public String zakaziObilazak(HttpServletRequest request, Model m) {
//		request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
//		request.getSession().removeAttribute("messageZakazivanje");
////		request.getSession().setAttribute("messageUpeh", "Uspešno je zakazan obilazak!");
//		m.addAttribute("obilazakDTO", new ObilazakDTO());
//		return "zakazivanjeObilazak";
//	}
//
//	@PostMapping("zakazi")
//	public String zakazi(@Valid ObilazakDTO obilazak, BindingResult bindingResult, @RequestParam("nekretnina")Integer idN, HttpServletRequest request, Model m) {
//		if (bindingResult.hasErrors()) {
//			System.out.println(bindingResult.getAllErrors());
//			m.addAttribute("errors", bindingResult.getAllErrors());
//		} else {
//			request.getSession().removeAttribute("messageZakazivanje");
//			if(obilazak.getNekretnina() == null) {
//				request.getSession().setAttribute("messageZakazivanje", "Nemate nijednu nekretninu!");
//				m.addAttribute("message", "Nemate nijednu nekretninu!");
//			}
//			if(obilazak.getDatum() == null) {
//				m.addAttribute("message", "Morate izabrati datum!");
//				request.getSession().setAttribute("messageZakazivanje", "Morate izabrati datum!");
//				throw new EmptyFieldsException("Morate uneti datum!");
//			}
//			int id = service.saveObilazak(obilazak, idN);
//			switch(id) {
//			case -1:
//				m.addAttribute("message", "Greška prilikom čuvanja nekretnine!");
//				request.getSession().setAttribute("messageZakazivanje", "Greška prilikom čuvanja nekretnine!");
//				break;
//			case -2:
//				m.addAttribute("message", "Datum mora biti posle današnjeg!");
//				request.getSession().setAttribute("messageZakazivanje", "Datum mora biti posle današnjeg!");
//				break;
//			case -3:
//				m.addAttribute("message", "Izabrana nekretnina ne postoji!");
//				request.getSession().setAttribute("messageZakazivanje", "Izabrana nekretnina ne postoji!");
//				break;
//			case -4:
//				m.addAttribute("message", "Izabrani korisnik ne postoji!");
//				request.getSession().setAttribute("messageZakazivanje", "Izabrani korisnik ne postoji!");
//				break;
//			default:
//				m.addAttribute("messageUspeh", "Uspešno je zakazan obilazak!");
//				request.getSession().setAttribute("messageUpeh", "Uspešno je zakazan obilazak!");
//			}
//		}
//
//		return "zakazivanjeObilazak";
//	}
//
//	@GetMapping("getObilasci")
//	public String getObilasci(HttpServletRequest request) {
//		request.getSession().removeAttribute("nekretnineK");
//		request.getSession().setAttribute("nekretnineK", service.getNekretnineKorisnika());
//		request.getSession().removeAttribute("obilasci");
//		request.getSession().removeAttribute("messageObilazak");
//		return "zakazaniObilasci";
//	}
//
//	@GetMapping("prikaziObilaske")
//	public String sviObilasci(HttpServletRequest request, @RequestParam("idN")Integer idN) {
//		request.getSession().removeAttribute("obilasci");
//		request.getSession().setAttribute("obilasci", service.getObilasciKorisnika(idN));
//		request.getSession().removeAttribute("messageObilazak");
//		request.getSession().removeAttribute("messageObilasci");
//		if(service.getObilasciKorisnika(idN).size() == 0) {
//			request.getSession().setAttribute("messageObilasci", "Nemate nijedan obilazak!");
//		}
//		return "zakazaniObilasci";
//	}
//
//	@GetMapping("ukloniObilazak")
//	public String ukloniObilazak(HttpServletRequest request, @RequestParam("idO")Integer idO) {
//		request.getSession().removeAttribute("messageObilazak");
//		if(service.deleteObilazak(idO))
//			request.getSession().setAttribute("messageObilazak", "Uspešno otkazan obilazak");
//		else
//			request.getSession().setAttribute("messageObilazak", "Došlo je do greške prilikom otkazivanja obilaska!");
////		request.getSession().removeAttribute("obilasci");
////		request.getSession().setAttribute("obilasci", service.getObilasciKorisnika(idN));
//		return "zakazaniObilasci";
//	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleNotFoundZException(RuntimeException e) {
		return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
	}

}
