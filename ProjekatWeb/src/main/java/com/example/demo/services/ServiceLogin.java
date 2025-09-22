package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.KorisnikValidation;
import com.example.demo.repositories.GradRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.NekretninaRepository;
import com.example.demo.repositories.ObilazakRepository;
import com.example.demo.repositories.OmiljeneRepository;
import com.example.demo.repositories.SlikaRepository;
import com.example.demo.repositories.TipNekretnineRepository;
import com.example.demo.repositories.UlogaRepository;

import model.Korisnik;
import model.Uloga;

@Service
public class ServiceLogin {
	
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
	
	public List<Uloga> getUloge() {
		List<Uloga> uloge = ur.findAll();
		List<Uloga> bezAdmin = new ArrayList<>();
		for (Uloga u : uloge) {
			if(!u.getNaziv().equalsIgnoreCase("ADMIN")) {
				bezAdmin.add(u);
			}
		}
		return bezAdmin;
	}
	
	public Korisnik getKorisnik(String user, String pass) {
		return kr.findByUsernameAndPassword(user, pass);
	}
	
	public boolean login(String user, String pass) {
		if(kr.findByUsernameAndPassword(user, pass) == null) {
			return false;
		}
		return true;
	}
	
	public boolean usernameOk(String user) {
		if(kr.findByUsername(user) == null) return false;
		return true;
	}
	
	public String tipK(String user, String pass) {
		return kr.findByUsernameAndPassword(user, pass).getUloga().getNaziv();
	}
	
	@Transactional
	public void registracija(Korisnik k) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     	k.setPassword(passwordEncoder.encode(k.getPassword()));
		k.getUloga().addKorisnik(k);
    	kr.save(k);
	}
	
	@Transactional
	public void sacuvajKorisnika(KorisnikValidation k) {
		Korisnik noviKorisnik = new Korisnik();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     	noviKorisnik.setPassword(passwordEncoder.encode(k.getPassword()));
		noviKorisnik.setUsername(k.getUsername());
		noviKorisnik.setIme(k.getIme());
		noviKorisnik.setPrezime(k.getPrezime());
		noviKorisnik.setEmail(k.getEmail());
		noviKorisnik.setUloga(ur.findById(k.getUloga()).get());
    	kr.save(noviKorisnik);
	}

}
