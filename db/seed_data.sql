-- -----------------------------------------------------
-- Populate Grad (Cities)
-- -----------------------------------------------------
INSERT INTO Grad (idGrad, naziv) VALUES
(1, 'Beograd'), (2, 'Novi Sad'), (3, 'Inđija'), (4, 'Subotica'),
(5, 'Niš'), (6, 'Ruma'), (7, 'Kraljevo'), (8, 'Užice'),
(9, 'Šabac'), (10, 'Priština'), (11, 'Sremska Mitrovica'),
(12, 'Vršac'), (13, 'Vranje'), (14, 'Aranđelovac'), (15, 'Zrenjanin');

-- -----------------------------------------------------
-- Populate TipNekretnine (Property Types)
-- -----------------------------------------------------
INSERT INTO TipNekretnine (idTipNekretnine, naziv) VALUES
(1, 'Kuća'), (2, 'Stan'), (3, 'Vikendica'), (4, 'Garsonjera'), (5, 'Dupleks');

-- -----------------------------------------------------
-- Populate Uloga (User Roles)
-- -----------------------------------------------------
INSERT INTO Uloga (idUloga, naziv) VALUES
(1, 'ADMIN'), (2, 'PRODAVAC'), (3, 'KUPAC');

-- -----------------------------------------------------
-- Populate Korisnik (Sample Users)
-- Passwords are hashed with BCrypt:
-- "admin123", "seller123", "buyer123"
-- -----------------------------------------------------
INSERT INTO Korisnik (idKorisnik, ime, prezime, email, username, password, Uloga_idUloga) VALUES
(1, 'Admin', 'User', 'admin@example.com', 'admin', '$2a$10$7qfM2u6fY1zHf1T3Y5p4AOe2z5v1R4u1FhBSpUZdFzQ0F1RzpD3eG', 1),
(2, 'Marko', 'Markovic', 'seller@example.com', 'seller', '$2a$10$1hG9ZQK2xDj1OypF0dBz2OJGwU3Z3g4JHdRf1P2jM8Qn8iMZqF1Te', 2),
(3, 'Jana', 'Jankovic', 'buyer@example.com', 'buyer', '$2a$10$8nK5VhT3cPj6L7dU2eRz0uNwA0vL1wE2GfQyT4sN7bM6fT5QxW3Ye', 3);
