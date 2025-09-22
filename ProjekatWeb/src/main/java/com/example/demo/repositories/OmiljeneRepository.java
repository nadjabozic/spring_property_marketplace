package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;
import model.Nekretnina;
import model.Omiljene;

public interface OmiljeneRepository extends JpaRepository<Omiljene, Integer> {

	@Query("select o from Omiljene o where o.nekretnina.grad.naziv=:naziv order by o.nekretnina.tipnekretnine.naziv")
	List<Omiljene> getOmiljenesByGrad(@Param("naziv") String grad);

	@Query("select o from Omiljene o where o.korisnik=:k and o.nekretnina=:n")
	Omiljene findByKorisnikAndNekretnina(Korisnik k, Nekretnina n);
	
}
