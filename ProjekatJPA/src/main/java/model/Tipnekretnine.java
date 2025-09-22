package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipnekretnine database table.
 * 
 */
@Entity
@NamedQuery(name="Tipnekretnine.findAll", query="SELECT t FROM Tipnekretnine t")
public class Tipnekretnine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipNekretnine;

	private String naziv;

	//bi-directional many-to-one association to Nekretnina
	@OneToMany(mappedBy="tipnekretnine")
	private List<Nekretnina> nekretninas;

	public Tipnekretnine() {
	}

	public int getIdTipNekretnine() {
		return this.idTipNekretnine;
	}

	public void setIdTipNekretnine(int idTipNekretnine) {
		this.idTipNekretnine = idTipNekretnine;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Nekretnina> getNekretninas() {
		return this.nekretninas;
	}

	public void setNekretninas(List<Nekretnina> nekretninas) {
		this.nekretninas = nekretninas;
	}

	public Nekretnina addNekretnina(Nekretnina nekretnina) {
		getNekretninas().add(nekretnina);
		nekretnina.setTipnekretnine(this);

		return nekretnina;
	}

	public Nekretnina removeNekretnina(Nekretnina nekretnina) {
		getNekretninas().remove(nekretnina);
		nekretnina.setTipnekretnine(null);

		return nekretnina;
	}

}