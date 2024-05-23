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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        eventDao = new EventDAO();

        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Nome", "Descrição", "Data de Início", "Data de Término"}, 0);
        eventTable = new JTable(tableModel);

        JButton sortByDateButton = new JButton("Ordenar por Data de Início");
        sortByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                loadEvents("date");
            }
        });

            JButton sortByNameButton = new JButton("Ordenar por Nome");
            sortByNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    loadEvents("name");
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
        tableModel.setRowCount(0);

        List<Event> events = eventDao.getUpcomingEvents(sortBy);
        for(Event event : events) {
            tableModel.addRow(new Object[]{
                event.getName(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventViewScreen().setVisible(true);
            }
        });
    }
}
