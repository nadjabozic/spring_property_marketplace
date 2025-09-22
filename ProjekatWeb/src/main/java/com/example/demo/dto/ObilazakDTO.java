package com.example.demo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ObilazakDTO {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datum;
	private Integer nekretnina;

	public Integer getNekretnina() {
		return nekretnina;
	}

	public void setNekretnina(Integer nekretnina) {
		this.nekretnina = nekretnina;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
