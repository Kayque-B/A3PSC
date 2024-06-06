package com.a3psc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventRegistrationScreen extends JFrame{
    private JTextField nameField;
    private JTextArea descriptionField;
    private JTextField startDateField;
    private JTextField endDateField;

    public EventRegistrationScreen() {
        setTitle ("Cadastro de Evento");
        setSize (400, 300);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo (null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Nome: ");
        nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Descrição: ");
        descriptionField = new JTextArea();
        JLabel startDateLabel = new JLabel("Data de início (dd-mm-aaaa hh:mm:ss)");
        startDateField = new JTextField();
        JLabel endDateLabel = new JLabel("Data de término (dd-mm-aaaa hh:mm:ss)");
        endDateField = new JTextField();

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new RegisterButtonListener());

        // Adiciona componentes ao painel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionField));
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(registerButton);

        add(panel); // Adiciona painel à tela
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText(); // Obtém o texto do campo de nome
            String description = descriptionField.getText();
            String startDateStr = startDateField.getText();
            String endDateStr = endDateField.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-aaaa HH:mm:ss");
            try {
                Timestamp startDate = new Timestamp(dateFormat.parse(startDateStr).getTime()); // Converte a string de data de início para Timestamp
                Timestamp endDate = new Timestamp(dateFormat.parse(endDateStr).getTime());

                EventDAO eventDao = new EventDAO(); // Cria uma instância do EventDAO
                if (eventDao.registerEvent(name, description, startDate, endDate)) { // Chama o método registerEvent do EventDAO
                    JOptionPane.showMessageDialog(null, "Evento Registrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar evento.");
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de data inválido");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventRegistrationScreen().setVisible(true); // Torna a tela de cadastro de evento visível
            }
        });
    }
}
