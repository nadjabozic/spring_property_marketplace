package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.GradDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.TipnekretnineDTO;
import com.example.demo.dto.UlogaDTO;
import com.example.demo.repositories.GradRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.NekretninaRepository;
import com.example.demo.repositories.ObilazakRepository;
import com.example.demo.repositories.OmiljeneRepository;
import com.example.demo.repositories.SlikaRepository;
import com.example.demo.repositories.TipNekretnineRepository;
import com.example.demo.repositories.UlogaRepository;

import model.Grad;
import model.Korisnik;
import model.Tipnekretnine;
import model.Uloga;

@Service
public class ServiceRest {
	
	@Autowired
	GradRepository gr;
	
	@Autowired
	TipNekretnineRepository tnr;
	
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	SlikaRepository sr;
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	OmiljeneRepository omr;
	
	@Autowired
	ObilazakRepository or;
	
	@Autowired
	NekretninaRepository nr;
	
	
	@Transactional
	public int saveGrad(GradDTO grad) {
		Grad noviGrad = new Grad();
		noviGrad.setNaziv(grad.getNaziv());
		try {
			Grad g = gr.save(noviGrad);
			return g.getIdGrad();
		}catch(Exception ex) {
			return -1;
		}
	}
	
	@Transactional
	public int saveTipnekretnine(TipnekretnineDTO tn) {
		Tipnekretnine novitn = new Tipnekretnine();
		novitn.setNaziv(tn.getNaziv());
		try {
			Tipnekretnine t = tnr.save(novitn);
			return t.getIdTipNekretnine();
		}catch(Exception ex) {
			return -1;
		}
	}
	
	@Transactional
	public int saveUloga(UlogaDTO uloga) {
		Uloga novaUloga = new Uloga();
		novaUloga.setNaziv(uloga.getNaziv());
		try {
			Uloga u = ur.save(novaUloga);
			return u.getIdUloga();
		}catch(Exception ex) {
			return -1;
		}
	}
	
	public Tipnekretnine getNekretnina(String naziv) {
		return tnr.findByNaziv(naziv);
	}
	
	public Uloga getUloga(String naziv) {
		return ur.findByNaziv(naziv);
	}
	
	@Transactional
	public int saveKorisnik(KorisnikDTO korisnik, String naziv) {
		Korisnik noviKorisnik = new Korisnik();
		noviKorisnik.setIme(korisnik.getIme());
		noviKorisnik.setPrezime(korisnik.getPrezime());
		noviKorisnik.setEmail(korisnik.getEmail());
		noviKorisnik.setUsername(korisnik.getUsername());
		noviKorisnik.setPassword(korisnik.getPassword());
		noviKorisnik.setUloga(ur.findByNaziv(naziv));
		try {
			Korisnik k = kr.save(noviKorisnik);
			return k.getIdKorisnik();
		}catch(Exception ex) {
			return -1;
		}
	}

}
