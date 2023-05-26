package com.ynov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagotchiGUI extends JFrame {
    private Tamagotchi tamagotchi;
    private JLabel hungerLabel;
    private JLabel happinessLabel;

    public TamagotchiGUI(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
        String playerName = askPlayerName();
        tamagotchi.setName(playerName);
        initializeUI();
        setTitle("Tamagotchi - " + playerName);
    }

    private void initializeUI() {
        setTitle("Tamagotchi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création des boutons
        JButton feedButton = new JButton("Nourrir");
        JButton playButton = new JButton("Jouer");

        // Création des étiquettes
        hungerLabel = new JLabel("Faim : " + tamagotchi.getHunger());
        happinessLabel = new JLabel("Bonheur : " + tamagotchi.getHappiness());

        // Ajout des boutons et des étiquettes au panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(feedButton);
        mainPanel.add(playButton);
        add(mainPanel, BorderLayout.CENTER);

        // Ajout des étiquettes en bas
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        statusPanel.add(hungerLabel);
        statusPanel.add(happinessLabel);
        add(statusPanel, BorderLayout.SOUTH);

        // Définition des actions des boutons
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tamagotchi.feed();
                hungerLabel.setText("Faim : " + tamagotchi.getHunger());
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tamagotchi.play();
                happinessLabel.setText("Bonheur : " + tamagotchi.getHappiness());
            }
        });

        pack();
        setVisible(true);
    }

    private String askPlayerName() {
        return JOptionPane.showInputDialog(this, "Entrez le nom de votre Tamagotchi :");
    }

    public static void main(String[] args) {
        Tamagotchi tamagotchi = new Tamagotchi("");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TamagotchiGUI(tamagotchi);
            }
        });
    }
}
