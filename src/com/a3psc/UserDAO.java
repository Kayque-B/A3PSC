package com.a3psc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean authenticate(String email, String password) {
        String query = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }

      // Método para registrar um novo usuário
        public boolean registerUser(String name, int age, String gender, String email, String password, String cpf) {
        String query = "INSERT INTO usuarios (nome, idade, sexo, email, senha, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?, 'COMUM')";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name); // Define o parâmetro nome
            stmt.setInt(2, age); // Define o parâmetro idade
            stmt.setString(3, gender); // Define o parâmetro sexo
            stmt.setString(4, email); // Define o parâmetro email
            stmt.setString(5, password); // Define o parâmetro senha
            stmt.setString(6, cpf); // Define o parâmetro cpf
            stmt.executeUpdate(); // Executa a atualização no banco de dados
            return true; // Retorna true se a operação for bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
         return false; // Retorna false se ocorrer uma exceção
        }
        }
}