package com.tamagochi;

import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    private static final String FICHIER_SAUVEGARDE = "tamagotchi.sav";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1. Mode en ligne de commande");
        System.out.println("2. Mode avec interface graphique");

        int choix = scanner.nextInt();
        if (choix == 1) {
            launchCommandLine();
        } else if (choix == 2) {
            launchGui(args);
        } else {
            System.out.println("Choix invalide");
        }
    }

    private static void launchCommandLine() {
        Tamagotchi tamagotchi = chargerSauvegarde();

        if (tamagotchi == null) {
            tamagotchi = new Tamagotchi();
            System.out.println("Nouveau Tamagotchi créé !");
        } else {
            System.out.println("Tamagotchi chargé depuis la sauvegarde !");
        }

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
            System.out.println("7. Sauvegarder et quitter");
            System.out.println("8. Quitter sans sauvegarder");

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
                    sauvegarderTamagotchi(tamagotchi);
                    System.out.println("Tamagotchi sauvegardé. Au revoir !");
                    System.exit(0);
                case 8:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static Tamagotchi chargerSauvegarde() {
        Tamagotchi tamagotchi = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHIER_SAUVEGARDE))) {
            tamagotchi = (Tamagotchi) ois.readObject();
            System.out.println("Tamagotchi chargé depuis la sauvegarde !");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aucune sauvegarde trouvée. Création d'un nouveau Tamagotchi !");
        }
        return tamagotchi;
    }

    private static void sauvegarderTamagotchi(Tamagotchi tamagotchi) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHIER_SAUVEGARDE))) {
            oos.writeObject(tamagotchi);
            System.out.println("Tamagotchi sauvegardé !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du Tamagotchi : " + e.getMessage());
        }
    }

    private static void launchGui(String[] args) {
        Application.launch(Gui.class, args);
    }
}
