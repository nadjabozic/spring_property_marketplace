# Real Estate Management Project

This project is a **Spring Boot** web application for managing real estate listings, users, and reports. It consists of two main modules:

- **ProjekatJPA** – contains JPA entities, repositories, and service layer for database operations.
- **ProjekatWeb** – contains Spring MVC controllers, JSP views, security configuration, and JasperReports integration.
- **uploads** – directory for storing uploaded files (images, documents, etc.). This folder is ignored in Git.

---

## Features

- User roles: `ADMIN`, `PRODAVAC` (seller), `KUPAC` (buyer)
- Secure login and role-based access control using Spring Security
- CRUD operations for real estate listings (`Nekretnina`) and property types (`TipNekretnine`)
- Dynamic JasperReports generation for:
  - Favorite properties by city
  - Property prices by type
  - Visits/appointments reports
- Responsive JSP-based web interface

---

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL or PostgreSQL database
- Git (for cloning/pushing the project)
- Optional: IDE like IntelliJ IDEA or Eclipse

---

## Installation & Setup

1. **Clone the repository:**

```
bash git clone https://github.com/nadjabozic/spring_property_marketplace.git cd real-estate-project
```

2. **Create the database and tables using the provided schema script:**

```
mysql -u root -p < db/property_marketplace_schema.sql
```

3. **Populate initial sample data:**

```
mysql -u root -p property_marketplace < db/seed_data.sql
```

4. **Configure the database in application.properties (or application.yml) of ProjekatWeb:**

```
spring.datasource.url=jdbc:mysql://localhost:3306/property_marketplace
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

5. **Build the project with Maven:**

```
mvn clean install
```

6. **Run the application:**

```
mvn spring-boot:run
```

The app will start on http://localhost:8080

## Usage

Navigate to the index page: /index

Use the predefined admin user (or create one in the database):
Username: admin
Password: admin123 (make sure to hash the password using BCrypt)

Access role-specific pages:

/admin/** – Admin functionalities
/prodavac/** – Seller functionalities
/kupac/** – Buyer functionalities

Generate PDF reports through the admin dashboard.

## Project Structure

```
root/
├── ProjekatJPA/       # JPA entities, repositories, services
├── ProjekatWeb/       # Controllers, views, Spring Boot application
├── uploads/           # Uploaded files (ignored in Git)
├── db/                # Database scripts
│   ├── property_marketplace_schema.sql
│   └── seed_data.sql
├── .gitignore
└── README.md
```

## Security

Passwords are encrypted using BCryptPasswordEncoder.
Role-based access is enforced through Spring Security configuration.
Login form is accessible at /contrLogin/login.

## Reporting

Reports are generated using JasperReports and can be downloaded as PDF files:

- Favorite properties by city (`izvestaj1`)
- Property prices by type (`izvestaj2`)
- Visits/appointments report (`izvestaj3`)

## Database Sample Data

**Cities (Grad)**

```sql
INSERT INTO Grad (idGrad, naziv) VALUES
(1, 'Beograd'), (2, 'Novi Sad'), (3, 'Inđija'), (4, 'Subotica'),
(5, 'Niš'), (6, 'Ruma'), (7, 'Kraljevo'), (8, 'Užice'),
(9, 'Šabac'), (10, 'Priština'), (11, 'Sremska Mitrovica'),
(12, 'Vršac'), (13, 'Vranje'), (14, 'Aranđelovac'), (15, 'Zrenjanin');
```

**Property Types (TipNekretnine)**

```sql
INSERT INTO TipNekretnine (idTipNekretnine, naziv) VALUES
(1, 'Kuća'), (2, 'Stan'), (3, 'Vikendica'), (4, 'Garsonjera'), (5, 'Dupleks');
```

**User Roles (Uloga)**

```sql
INSERT INTO Uloga (idUloga, naziv) VALUES
(1, 'ADMIN'), (2, 'PRODAVAC'), (3, 'KUPAC');
```

**Sample Users (Korisnik)**

-- Passwords are hashed with BCrypt:
-- admin123, seller123, buyer123

```sql
INSERT INTO Korisnik (idKorisnik, ime, prezime, email, username, password, Uloga_idUloga) VALUES
(1, 'Admin', 'User', 'admin@example.com', 'admin', '$2a$10$7qfM2u6fY1zHf1T3Y5p4AOe2z5v1R4u1FhBSpUZdFzQ0F1RzpD3eG', 1),
(2, 'Marko', 'Markovic', 'seller@example.com', 'seller', '$2a$10$1hG9ZQK2xDj1OypF0dBz2OJGwU3Z3g4JHdRf1P2jM8Qn8iMZqF1Te', 2),
(3, 'Jana', 'Jankovic', 'buyer@example.com', 'buyer', '$2a$10$8nK5VhT3cPj6L7dU2eRz0uNwA0vL1wE2GfQyT4sN7bM6fT5QxW3Ye', 3);
```
