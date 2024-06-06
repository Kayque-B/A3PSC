package com.a3psc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventViewScreen extends JFrame {
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private EventDAO eventDao;

    public EventViewScreen() {
        setTitle("Visualização de eventos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        eventDao = new EventDAO(); // Cria uma instância do EventDAO

        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Nome", "Descrição", "Data de Início", "Data de Término"}, 0);
        eventTable = new JTable(tableModel);

        JButton sortByDateButton = new JButton("Ordenar por Data de Início");
        sortByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                loadEvents("date"); // Carrega eventos ordenados por data de início
            }
        });

            JButton sortByNameButton = new JButton("Ordenar por Nome");
            sortByNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    loadEvents("name"); // Carrega eventos ordenados por nome
                }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortByNameButton);
        buttonPanel.add(sortByDateButton);

        panel.add(new JScrollPane(eventTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void loadEvents(String sortBy) {
        tableModel.setRowCount(0); // Limpa a tabela

        List<Event> events = eventDao.getUpcomingEvents(sortBy); // Obtém os eventos ordenados
        for (Event event : events) {
            tableModel.addRow(new Object[]{
                event.getName(), // Adiciona o nome do evento
                event.getDescription(), // Adiciona a descrição do evento
                event.getStartDate(), // Adiciona a data de início do evento
                event.getEndDate() // Adiciona a data de término do evento
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventViewScreen().setVisible(true); // Torna a tela de visualização de eventos visível
            }
        });
    }
}
