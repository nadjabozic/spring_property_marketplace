-- -----------------------------------------------------
-- Schema property_marketplace
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `property_marketplace`;
USE `property_marketplace`;

-- -----------------------------------------------------
-- Table Uloga
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Uloga` (
  `idUloga` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) NULL,
  PRIMARY KEY (`idUloga`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table TipNekretnine
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TipNekretnine` (
  `idTipNekretnine` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) NULL,
  PRIMARY KEY (`idTipNekretnine`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table Grad
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grad` (
  `idGrad` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) NULL,
  PRIMARY KEY (`idGrad`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table Korisnik
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(255) NULL,
  `prezime` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `username` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  `Uloga_idUloga` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  INDEX `fk_Korisnik_Uloga_idx` (`Uloga_idUloga` ASC) VISIBLE,
  CONSTRAINT `fk_Korisnik_Uloga`
    FOREIGN KEY (`Uloga_idUloga`)
    REFERENCES `Uloga` (`idUloga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table Nekretnina
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Nekretnina` (
  `idNekretnina` INT NOT NULL AUTO_INCREMENT,
  `kvadratura` INT NULL,
  `brojSpavacihSoba` INT NULL,
  `brojKupatila` INT NULL,
  `cena` INT NULL,
  `opis` VARCHAR(255) NULL,
  `adresa` VARCHAR(255) NULL,
  `TipNekretnine_idTipNekretnine` INT NOT NULL,
  `Grad_idGrad` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL, 
  PRIMARY KEY (`idNekretnina`),
  INDEX `fk_Nekretnina_TipNekretnine_idx` (`TipNekretnine_idTipNekretnine` ASC) VISIBLE,
  INDEX `fk_Nekretnina_Grad_idx` (`Grad_idGrad` ASC) VISIBLE,
  INDEX `fk_Nekretnina_Korisnik_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Nekretnina_TipNekretnine`
    FOREIGN KEY (`TipNekretnine_idTipNekretnine`)
    REFERENCES `TipNekretnine` (`idTipNekretnine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nekretnina_Grad`
    FOREIGN KEY (`Grad_idGrad`)
    REFERENCES `Grad` (`idGrad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nekretnina_Korisnik`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table Slika
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Slika` (
  `idSlika` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `Nekretnina_idNekretnina` INT NOT NULL,
  PRIMARY KEY (`idSlika`),
  INDEX `fk_Slika_Nekretnina_idx` (`Nekretnina_idNekretnina` ASC) VISIBLE,
  CONSTRAINT `fk_Slika_Nekretnina`
    FOREIGN KEY (`Nekretnina_idNekretnina`)
    REFERENCES `Nekretnina` (`idNekretnina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table Obilazak
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Obilazak` (
  `idObilazak` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NULL,
  `Nekretnina_idNekretnina` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  PRIMARY KEY (`idObilazak`),
  INDEX `fk_Obilazak_Nekretnina_idx` (`Nekretnina_idNekretnina` ASC) VISIBLE,
  INDEX `fk_Obilazak_Korisnik_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Obilazak_Nekretnina`
    FOREIGN KEY (`Nekretnina_idNekretnina`)
    REFERENCES `Nekretnina` (`idNekretnina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Obilazak_Korisnik`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table Omiljene
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Omiljene` (
  `idOmiljene` INT NOT NULL AUTO_INCREMENT,
  `Nekretnina_idNekretnina` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  PRIMARY KEY (`idOmiljene`),
  INDEX `fk_Omiljene_Nekretnina_idx` (`Nekretnina_idNekretnina` ASC) VISIBLE,
  INDEX `fk_Omiljene_Korisnik_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Omiljene_Nekretnina`
    FOREIGN KEY (`Nekretnina_idNekretnina`)
    REFERENCES `Nekretnina` (`idNekretnina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Omiljene_Korisnik`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB;
