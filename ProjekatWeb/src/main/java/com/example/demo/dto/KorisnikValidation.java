package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class KorisnikValidation {

    @NotBlank(message = "Ime je obavezno")
    @Size(min = 2, max = 50, message = "Ime mora imati između 2 i 50 slova")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    @Size(min = 2, max = 50, message = "Prezime mora imati između 2 i 50 slova")
    private String prezime;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Nije dobar format email-a")
    private String email;

    @NotBlank(message = "Korisničko ime je obavezno")
    @Size(min = 2, max = 50, message = "Korisničko mora imati između 2 i 50 slova")
    private String username;

    @NotBlank(message = "Šifra je obavezna")
    @Size(min = 5, message = "Šifra mora imati bar 4 slova")
    private String password;

    @NotNull(message = "Uloga je obavezna")
    private Integer uloga;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUloga() {
        return uloga;
    }

    public void setUloga(Integer uloga) {
        this.uloga = uloga;
    }
}

