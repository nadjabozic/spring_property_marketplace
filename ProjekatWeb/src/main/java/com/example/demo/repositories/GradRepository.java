package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Grad;

public interface GradRepository extends JpaRepository<Grad, Integer> {
	
	Grad findByNaziv(String naziv);
	
	@Query("select g from Grad g order by g.naziv")
	List<Grad> getSorted();

}
