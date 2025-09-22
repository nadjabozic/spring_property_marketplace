package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tipnekretnine;

public interface TipNekretnineRepository extends JpaRepository<Tipnekretnine, Integer> {

	Tipnekretnine findByNaziv(String naziv);
	
}
