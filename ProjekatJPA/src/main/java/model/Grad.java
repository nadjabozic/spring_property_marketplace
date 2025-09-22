package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;


/**
 * The persistent class for the grad database table.
 * 
 */
@Entity
@NamedQuery(name="Grad.findAll", query="SELECT g FROM Grad g")
public class Grad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGrad;

	private String naziv;

	//bi-directional many-to-one association to Nekretnina
	@OneToMany(mappedBy="grad")
	private List<Nekretnina> nekretninas;

	public Grad() {
	}

	public int getIdGrad() {
		return this.idGrad;
	}

	public void setIdGrad(int idGrad) {
		this.idGrad = idGrad;
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
		nekretnina.setGrad(this);

		return nekretnina;
	}

	public Nekretnina removeNekretnina(Nekretnina nekretnina) {
		getNekretninas().remove(nekretnina);
		nekretnina.setGrad(null);

		return nekretnina;
	}

}