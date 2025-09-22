package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String email;

	private String ime;

	private String password;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	private Uloga uloga;

	//bi-directional many-to-one association to Nekretnina
	@OneToMany(mappedBy="prodavac", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Nekretnina> nekretninas;

	//bi-directional many-to-one association to Obilazak
	@OneToMany(mappedBy="korisnik", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Obilazak> obilazaks;

	//bi-directional many-to-one association to Omiljene
	@OneToMany(mappedBy="korisnik", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Omiljene> omiljenes;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Nekretnina> getNekretninas() {
		return this.nekretninas;
	}

	public void setNekretninas(List<Nekretnina> nekretninas) {
		this.nekretninas = nekretninas;
	}

	public Nekretnina addNekretnina(Nekretnina nekretnina) {
		getNekretninas().add(nekretnina);
		nekretnina.setProdavac(this);

		return nekretnina;
	}

	public Nekretnina removeNekretnina(Nekretnina nekretnina) {
		getNekretninas().remove(nekretnina);
		nekretnina.setProdavac(null);

		return nekretnina;
	}

	public List<Obilazak> getObilazaks() {
		return this.obilazaks;
	}

	public void setObilazaks(List<Obilazak> obilazaks) {
		this.obilazaks = obilazaks;
	}

	public Obilazak addObilazak(Obilazak obilazak) {
		getObilazaks().add(obilazak);
		obilazak.setKorisnik(this);

		return obilazak;
	}

	public Obilazak removeObilazak(Obilazak obilazak) {
		getObilazaks().remove(obilazak);
		obilazak.setKorisnik(null);

		return obilazak;
	}

	public List<Omiljene> getOmiljenes() {
		return this.omiljenes;
	}

	public void setOmiljenes(List<Omiljene> omiljenes) {
		this.omiljenes = omiljenes;
	}

	public Omiljene addOmiljene(Omiljene omiljene) {
		getOmiljenes().add(omiljene);
		omiljene.setKorisnik(this);

		return omiljene;
	}

	public Omiljene removeOmiljene(Omiljene omiljene) {
		getOmiljenes().remove(omiljene);
		omiljene.setKorisnik(null);

		return omiljene;
	}

}