package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;
import model.Nekretnina;
import model.Obilazak;

public interface ObilazakRepository extends JpaRepository<Obilazak, Integer> {

	List<Obilazak> findByKorisnikAndNekretnina(Korisnik k, Nekretnina n);
	
	@Query("select o from Obilazak o where o.datum >= :datum and o.nekretnina.tipnekretnine.naziv=:tip order by o.nekretnina.grad.naziv")
	List<Obilazak> obilasciPoDatumuITipu(@Param("datum")Date datum, @Param("tip")String nazivT);
	
}
