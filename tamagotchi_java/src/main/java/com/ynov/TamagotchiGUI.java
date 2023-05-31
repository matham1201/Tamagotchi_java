package com.ynov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagotchiGUI extends JFrame implements ActionListener {
    private Tamagotchi tamagotchi;
    private JProgressBar vieProgressBar;
    private JButton nourrirButton;
    private JButton laverButton;
    private JButton toiletteButton;

    public TamagotchiGUI(String nom) {
        tamagotchi = new Tamagotchi(nom);

        vieProgressBar = new JProgressBar();
        vieProgressBar.setStringPainted(true);
        updateProgressBar();

        nourrirButton = new JButton("Nourrir");
        nourrirButton.addActionListener(this);

        laverButton = new JButton("Laver");
        laverButton.addActionListener(this);

        toiletteButton = new JButton("Faire sa toilette");
        toiletteButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(vieProgressBar);
        panel.add(nourrirButton);
        panel.add(laverButton);
        panel.add(toiletteButton);

        add(panel);
        setTitle("Tamagotchi");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        String couleur = JOptionPane.showInputDialog(this, "Entrez la couleur du Tamagotchi :");
        tamagotchi.setCouleur(couleur);
        String nomJoueur = JOptionPane.showInputDialog(this, "Entrez votre nom :");
        tamagotchi.setNomJoueur(nomJoueur);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nourrirButton) {
            tamagotchi.nourrir();
            updateProgressBar();
        } else if (e.getSource() == laverButton) {
            tamagotchi.laver();
            updateProgressBar();
        } else if (e.getSource() == toiletteButton) {
            tamagotchi.faireToilette();
            updateProgressBar();
        }
    }

    private void updateProgressBar() {
        vieProgressBar.setValue(tamagotchi.getVie());
        vieProgressBar.setString("Vie : " + tamagotchi.getVie() + "%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TamagotchiGUI("Tamagotchi");
            }
        });
    }
}
