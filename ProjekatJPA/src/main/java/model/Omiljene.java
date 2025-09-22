package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the omiljene database table.
 * 
 */
@Entity
@NamedQuery(name="Omiljene.findAll", query="SELECT o FROM Omiljene o")
public class Omiljene implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOmiljene;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Nekretnina
	@ManyToOne
	private Nekretnina nekretnina;

	public Omiljene() {
	}

	public int getIdOmiljene() {
		return this.idOmiljene;
	}

	public void setIdOmiljene(int idOmiljene) {
		this.idOmiljene = idOmiljene;
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