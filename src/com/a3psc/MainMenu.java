package com.a3psc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    private JMenu userMenu;
    private JMenu eventMenu;
    private JMenuItem loginMenuItem;
    private JMenuItem registerMenuItem;
    private JMenuItem viewEventsMenuItem;
    private JMenuItem registerEventMenuItem;

    public MainMenu() {
        setTitle("Menu Principal");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        // cria a barra de menu
        menuBar = new JMenuBar();

        // Cria o menu do usuário
        userMenu = new JMenu("Usuário");
        loginMenuItem = new JMenuItem("Login");
        registerMenuItem = new JMenuItem("Registrar");
        userMenu.add(loginMenuItem); // Adiciona a opção de login ao menu de usuário
        userMenu.add(registerMenuItem); // Adiciona a opção de registro ao menu de usuário

        // Cria o menu de eventos
        eventMenu = new JMenu("Eventos");
        viewEventsMenuItem = new JMenuItem("Visualizar eventos");
        registerEventMenuItem = new JMenuItem("Registrar evento");
        eventMenu.add(viewEventsMenuItem);
        eventMenu.add(registerEventMenuItem);

        // Adiciona os menus à barra de menu
        menuBar.add(userMenu);
        menuBar.add(eventMenu);

        // Define a barra de menu no frame
        setJMenuBar(menuBar);

        // Adiciona listeners para as opções de menu
        loginMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().setVisible(true); // Abre a janela de login
            }
        });
        registerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen().setVisible(true); // Abre a tela de registro
            }
        });

        viewEventsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventViewScreen().setVisible(true); // Abre a tela de visualização de eventos
            }
        });

        registerEventMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventRegistrationScreen().setVisible(true); // Abre a tela de cadastro de evento
            }
        });
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true); // Torna o menu principal visível
            }
        });
    }
}
