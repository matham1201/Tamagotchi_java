package com.tamagochi;

import javafx.application.Application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1. Mode en ligne de commande");
        System.out.println("2. Mode avec interface graphique");

        int choix = scanner.nextInt();
        if (choix == 1) {
            launchCommandLine();
        } else if (choix == 2) {
            Application.launch(Gui.class, args);
        } else {
            System.out.println("Choix invalide");
        }
    }

    private static void launchCommandLine() {
        Tamagotchi tamagotchi = new Tamagotchi();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("Bonheur: " + tamagotchi.getBonheur());
            System.out.println("Etat de vie: " + tamagotchi.getEtatVie().toString());
            System.out.println("Temps écoulé depuis la dernière action: " + tamagotchi.getTempsDepuisDerniereAction());
            System.out.println("Etat de l'environnement: " + tamagotchi.getPropreteEnvironnement());
            System.out.println("\n");
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Jouer");
            System.out.println("2. Nourrir");
            System.out.println("3. Nettoyer");
            System.out.println("4. Soigner");
            System.out.println("5. Passer une unité de temps");
            System.out.println("6. Afficher l'état du Tamagotchi");
            System.out.println("7. Quitter");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    tamagotchi.jouer();
                    break;
                case 2:
                    tamagotchi.nourrir();
                    break;
                case 3:
                    tamagotchi.nettoyer();
                    break;
                case 4:
                    tamagotchi.soigner();
                    break;
                case 5:
                    tamagotchi.incrementerTemps();
                    break;
                case 6:
                    tamagotchi.afficherEtat();
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}
