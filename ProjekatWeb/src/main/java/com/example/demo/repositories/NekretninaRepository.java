package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Nekretnina;

public interface NekretninaRepository extends JpaRepository<Nekretnina, Integer> {

	@Query("select n from Nekretnina n where n.tipnekretnine.naziv=:naziv and n.cena >= :cena order by n.grad.naziv")
	List<Nekretnina> getNekretnineByTip(@Param("naziv")String tip, @Param("cena")Integer cena);
	
	@Query("SELECT n FROM Nekretnina n WHERE "
			+ "n.tipnekretnine.naziv = :tipNekretnine "
			+ "AND n.grad.naziv = :grad "
			+ "AND (:minPrice IS NULL OR n.cena >= :minPrice) "
			+ "AND (:maxPrice IS NULL OR n.cena <= :maxPrice) "
			+ "AND (:minSize IS NULL OR n.kvadratura >= :minSize) "
			+ "AND (:maxSize IS NULL OR n.kvadratura <= :maxSize) "
			+ "AND (:minBedrooms IS NULL OR n.brojSpavacihSoba >= :minBedrooms) "
			+ "AND (:minBathrooms IS NULL OR n.brojKupatila >= :minBathrooms)")
	List<Nekretnina> findByCriteria(
			@Param("tipNekretnine") String tipNekretnine,
	        @Param("grad") String grad,
	        @Param("minPrice") Integer minPrice,
	        @Param("maxPrice") Integer maxPrice,
	        @Param("minSize") Integer minSize,
	        @Param("maxSize") Integer maxSize,
	        @Param("minBedrooms") Integer minBedrooms,
	        @Param("minBathrooms") Integer minBathrooms);
	
}
