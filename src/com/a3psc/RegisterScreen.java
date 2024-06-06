package com.a3psc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreen extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField cpfField;

    public RegisterScreen() {
        setTitle("Registro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Nome:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Idade:");
        ageField = new JTextField();
        JLabel genderLabel = new JLabel("Sexo:");
        genderField = new JComboBox<>(new String[]{"M", "F", "O"});
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new RegisterButtonListener());

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(registerButton);

        add(panel);
    }

    private class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText(); // Obtém o texto do campo de nome
            int age = Integer.parseInt(ageField.getText()); // Converte o texto do campo de idade para inteiro
            String gender = (String) genderField.getSelectedItem(); // Obtém o item selecionado do campo de gênero
            String email = emailField.getText(); // Obtém o texto do campo de email
            String password = new String(passwordField.getPassword()); // Obtém o texto do campo de senha e converte para String
            String cpf = cpfField.getText(); // Obtém o texto do campo de CPF

            UserDAO userDao = new UserDAO(); // Cria uma instância do UserDAO
            if (userDao.registerUser(name, age, gender, email, password, cpf)) { // Chama o método registerUser do UserDAO
                JOptionPane.showMessageDialog(null, "Registro bem-sucedido!"); // Mostra uma mensagem de sucesso se o registro for bem-sucedido
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar usuário."); // Mostra uma mensagem de erro se o registro falhar
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterScreen().setVisible(true); // Torna a tela de registro visível
            }
        });
    }
}