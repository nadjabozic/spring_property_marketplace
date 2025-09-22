package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdminInserter {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/property_marketplace";
        String username = "root";
        String password = "mysqlpassword";

        String rawPassword = "pass789";

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);

        String sql = "INSERT INTO korisnik (username, password, Uloga_idUloga) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "admin");
            stmt.setString(2, hashedPassword);
            stmt.setString(3, "1");

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Admin uspešno dodat u bazu!");
            } else {
                System.out.println("Nešto je pošlo po zlu.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
