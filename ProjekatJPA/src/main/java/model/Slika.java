package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the slika database table.
 * 
 */
@Entity
@NamedQuery(name="Slika.findAll", query="SELECT s FROM Slika s")
public class Slika implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSlika;

	private String url;

	//bi-directional many-to-one association to Nekretnina
	@ManyToOne
	private Nekretnina nekretnina;

	public Slika() {
	}

	public int getIdSlika() {
		return this.idSlika;
	}

	public void setIdSlika(int idSlika) {
		this.idSlika = idSlika;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Nekretnina getNekretnina() {
		return this.nekretnina;
	}

	public void setNekretnina(Nekretnina nekretnina) {
		this.nekretnina = nekretnina;
	}

}