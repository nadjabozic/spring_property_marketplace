package model;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the nekretnina database table.
 * 
 */
@Entity
@NamedQuery(name="Nekretnina.findAll", query="SELECT n FROM Nekretnina n")
public class Nekretnina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNekretnina;

	private String adresa;

	private int brojKupatila;

	private int brojSpavacihSoba;

	private int cena;

	private int kvadratura;

	private String opis;

	//bi-directional many-to-one association to Grad
	@ManyToOne
	private Grad grad;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik prodavac;

	//bi-directional many-to-one association to Tipnekretnine
	@ManyToOne
	private Tipnekretnine tipnekretnine;

	//bi-directional many-to-one association to Obilazak
	@OneToMany(mappedBy="nekretnina", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Obilazak> obilazaks;

	//bi-directional many-to-one association to Omiljene
	@OneToMany(mappedBy="nekretnina", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Omiljene> omiljenes;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="nekretnina", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Slika> slikas = new ArrayList<>();

	public Nekretnina() {
	}

	public int getIdNekretnina() {
		return this.idNekretnina;
	}

	public void setIdNekretnina(int idNekretnina) {
		this.idNekretnina = idNekretnina;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojKupatila() {
		return this.brojKupatila;
	}

	public void setBrojKupatila(int brojKupatila) {
		this.brojKupatila = brojKupatila;
	}

	public int getBrojSpavacihSoba() {
		return this.brojSpavacihSoba;
	}

	public void setBrojSpavacihSoba(int brojSpavacihSoba) {
		this.brojSpavacihSoba = brojSpavacihSoba;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getKvadratura() {
		return this.kvadratura;
	}

	public void setKvadratura(int kvadratura) {
		this.kvadratura = kvadratura;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Grad getGrad() {
		return this.grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public Korisnik getProdavac() {
		return this.prodavac;
	}

	public void setProdavac(Korisnik prodavac) {
		this.prodavac = prodavac;
	}

	public Tipnekretnine getTipnekretnine() {
		return this.tipnekretnine;
	}

	public void setTipnekretnine(Tipnekretnine tipnekretnine) {
		this.tipnekretnine = tipnekretnine;
	}

	public List<Obilazak> getObilazaks() {
		return this.obilazaks;
	}

	public void setObilazaks(List<Obilazak> obilazaks) {
		this.obilazaks = obilazaks;
	}

	public Obilazak addObilazak(Obilazak obilazak) {
		getObilazaks().add(obilazak);
		obilazak.setNekretnina(this);

		return obilazak;
	}

	public Obilazak removeObilazak(Obilazak obilazak) {
		getObilazaks().remove(obilazak);
		obilazak.setNekretnina(null);

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
		omiljene.setNekretnina(this);

		return omiljene;
	}

	public Omiljene removeOmiljene(Omiljene omiljene) {
		getOmiljenes().remove(omiljene);
		omiljene.setNekretnina(null);

		return omiljene;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setNekretnina(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setNekretnina(null);

		return slika;
	}

}