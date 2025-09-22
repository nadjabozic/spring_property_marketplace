package com.example.demo.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GradDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.TipnekretnineDTO;
import com.example.demo.dto.UlogaDTO;
import com.example.demo.exceptions.EmptyFieldsException;
import com.example.demo.exceptions.NotfoundException;
import com.example.demo.exceptions.SavingErrorException;
import com.example.demo.services.ServiceRest;

@RestController
@RequestMapping("/api/controller/")
public class ControllerR {

	@Autowired
	ServiceRest service;
	
	@PostMapping("saveGrad")
	public ResponseEntity<?> saveGrad(GradDTO grad) {
		if(grad.getNaziv() == "")
			throw new EmptyFieldsException("Nijedno polje ne sme ostati prazno");
		int id = service.saveGrad(grad);
		if(id == -1)
			throw new SavingErrorException("Doslo je greske prilikom cuvanja!");
		return ResponseEntity.ok("Uspesno sacuvan grad! Id novog grada je " + id);
	}
	
	@PostMapping("saveTipnekretnine")
	public ResponseEntity<?> saveTipnekretnine(TipnekretnineDTO tn) {
		if(tn.getNaziv() == "")
			throw new EmptyFieldsException("Nijedno polje ne sme ostati prazno");
		int id = service.saveTipnekretnine(tn);
		if(id == -1)
			throw new SavingErrorException("Doslo je greske prilikom cuvanja!");
		return ResponseEntity.ok("Uspesno sacuvan tip nekretnine! Id novog je " + id);
	}
	
	@PostMapping("saveUloga")
	public ResponseEntity<?> saveUloga(UlogaDTO u) {
		if(u.getNaziv() == "")
			throw new EmptyFieldsException("Nijedno polje ne sme ostati prazno");
		int id = service.saveUloga(u);
		if(id == -1)
			throw new SavingErrorException("Doslo je greske prilikom cuvanja!");
		return ResponseEntity.ok("Uspesno sacuvana uloga! Id novog je " + id);
	}
	
	@PostMapping("saveKorisnik")
	public ResponseEntity<?> saveKorisnik(KorisnikDTO k, String uloga) {
		if(uloga == "")
			throw new EmptyFieldsException("Nijedno polje ne sme ostati prazno");
		if(service.getUloga(uloga) == null) {
			throw new NotfoundException("Ne postoji uneta uloga", uloga);
		}
		int id = service.saveKorisnik(k, uloga);
		if(id == -1)
			throw new SavingErrorException("Doslo je greske prilikom cuvanja!");
		return ResponseEntity.ok("Uspesno sacuvana uloga! Id novog je " + id);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleNotFoundZException(RuntimeException e) {
		return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
	}
	
}
