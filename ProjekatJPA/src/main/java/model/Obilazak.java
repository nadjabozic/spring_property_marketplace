package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the obilazak database table.
 * 
 */
@Entity
@NamedQuery(name="Obilazak.findAll", query="SELECT o FROM Obilazak o")
public class Obilazak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idObilazak;

	@Temporal(TemporalType.DATE)
	private Date datum;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Nekretnina
	@ManyToOne
	private Nekretnina nekretnina;

	public Obilazak() {
	}

	public int getIdObilazak() {
		return this.idObilazak;
	}

	public void setIdObilazak(int idObilazak) {
		this.idObilazak = idObilazak;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Nekretnina getNekretnina() {
		return this.nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}

}