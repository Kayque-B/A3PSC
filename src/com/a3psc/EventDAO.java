package com.a3psc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    // Método para obter eventos futuros, ordenados por nome ou data de início
    public List<Event> getUpcomingEvents(String sortBy) {
        List<Event> events = new ArrayList<>();
        String orderBy = sortBy.equals("name") ? "nome" : "dataInicio";
        String query = "SELECT * FROM eventos WHERE dataTermino > NOW() ORDER BY " + orderBy;

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Event event = new Event();
                event.setId(rs.getInt("id")); // Define o ID do evento
                event.setName(rs.getString("nome")); // Define o nome do evento
                event.setDescription(rs.getString("descricao"));
                event.setStartDate(rs.getTimestamp("dataInicio"));
                event.setEndDate(rs.getTimestamp("dataTermino"));
                events.add(event); // Adiciona o evento à lista de eventos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events; // Retorna a lista de eventos
    }

    // Método para registrar um novo evento
    public boolean registerEvent(String name, String description, Timestamp startDate, Timestamp endDate) {
        String query = "INSERT INTO eventos (nome, descricao, dataInicio, dataTermino) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name); // Define o parâmetro nome
            stmt.setString(2, description);
            stmt.setTimestamp(3, startDate);
            stmt.setTimestamp(4, endDate);
            stmt.executeUpdate(); // Executa a atualização no banco de dados
            return true; // Retorna true se a operação for bem-sucedida
            } catch (SQLException e) {
                e.printStackTrace();
                return false; // Retorna false se ocorrer uma exceção
            }
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT * FROM eventos";
    
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
    
            while(rs.next()){
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("nome"));
                event.setDescription(rs.getString("descricao"));
                event.setStartDate(rs.getTimestamp("dataInicio"));
                event.setEndDate(rs.getTimestamp("dataTermino"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return events;
    }
}