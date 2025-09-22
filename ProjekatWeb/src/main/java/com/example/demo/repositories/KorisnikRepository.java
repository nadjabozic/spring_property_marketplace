package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	Korisnik findByUsernameAndPassword(String username, String password);
	
	Korisnik findByUsername(String username);
	
	Korisnik existsByUsername(String username);

}
