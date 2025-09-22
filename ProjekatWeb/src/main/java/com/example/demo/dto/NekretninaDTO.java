package com.example.demo.dto;

public class NekretninaDTO {
	
	private int kvadratura;
	private int brojSpavacihSoba;
	private int brojKupatila;
	private int cena;
	private String opis;
	private String adresa;
	private String tip;
	private String grad;
	private Integer minPrice;
    private Integer maxPrice;
    private Integer minSize;
    private Integer maxSize;
    private Integer minBedrooms;
    private Integer minBathrooms;
//	String url;
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
	public int getKvadratura() {
		return kvadratura;
	}
	public void setKvadratura(int kvadratura) {
		this.kvadratura = kvadratura;
	}
	public int getBrojSpavacihSoba() {
		return brojSpavacihSoba;
	}
	public void setBrojSpavacihSoba(int brojSpavacihSoba) {
		this.brojSpavacihSoba = brojSpavacihSoba;
	}
	public int getBrojKupatila() {
		return brojKupatila;
	}
	public void setBrojKupatila(int brojKupatila) {
		this.brojKupatila = brojKupatila;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getMinSize() {
		return minSize;
	}
	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Integer getMinBedrooms() {
		return minBedrooms;
	}
	public void setMinBedrooms(Integer minBedrooms) {
		this.minBedrooms = minBedrooms;
	}
	public Integer getMinBathrooms() {
		return minBathrooms;
	}
	public void setMinBathrooms(Integer minBathrooms) {
		this.minBathrooms = minBathrooms;
	}

}
