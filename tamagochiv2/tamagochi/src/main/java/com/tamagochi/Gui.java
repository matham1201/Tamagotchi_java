package com.tamagochi;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Gui extends Application {
    private Tamagotchi tamagotchi;
    private boolean useCommandLine;

    private Label bonheurLabel;
    private Label etatLabel;
    private Label tempsLabel;
    private Label propreteLabel;

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

    private static void launchGui(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        useCommandLine = false;
        tamagotchi = new Tamagotchi();

        primaryStage.setTitle("Tamagotchi");
        primaryStage.setResizable(false);

        // Création des éléments de l'interface
        Label bonheurTitleLabel = new Label("Bonheur:");
        bonheurLabel = new Label(String.valueOf(tamagotchi.getBonheur()));
        Label etatTitleLabel = new Label("Etat de vie:");
        etatLabel = new Label(tamagotchi.getEtatVie().toString());
        Label tempsTitleLabel = new Label("Temps écoulé depuis la dernière action:");
        tempsLabel = new Label(String.valueOf(tamagotchi.getTempsDepuisDerniereAction()));
        Label propreteTitleLabel = new Label("Etat de l'environnement :");
        propreteLabel = new Label(String.valueOf(tamagotchi.getPropreteEnvironnement()));

        Button jouerButton = new Button("Jouer");
        Button nourrirButton = new Button("Nourrir");
        Button nettoyerButton = new Button("Nettoyer");
        Button soignerButton = new Button("Soigner");
        Button incrementerTempsButton = new Button("Passer une unité de temps");
        Button afficherEtatButton = new Button("Afficher l'état du Tamagotchi");

        // Ajout des actions aux boutons
        jouerButton.setOnAction(e -> tamagotchi.jouer());
        nourrirButton.setOnAction(e -> tamagotchi.nourrir());
        nettoyerButton.setOnAction(e -> tamagotchi.nettoyer());
        soignerButton.setOnAction(e -> tamagotchi.soigner());
        incrementerTempsButton.setOnAction(e -> tamagotchi.incrementerTemps());
        afficherEtatButton.setOnAction(e -> tamagotchi.afficherEtat());

        // Création du layout principal
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(
                bonheurTitleLabel, bonheurLabel,
                etatTitleLabel, etatLabel,
                tempsTitleLabel, tempsLabel,
                propreteTitleLabel, propreteLabel,
                jouerButton, nourrirButton, nettoyerButton, soignerButton, incrementerTempsButton, afficherEtatButton
        );

        // Mise à jour des labels en fonction de l'état du Tamagotchi
        tamagotchi.setOnTamagotchiUpdate(() -> {
            Platform.runLater(() -> {
                bonheurLabel.setText(String.valueOf(tamagotchi.getBonheur()));
                etatLabel.setText(tamagotchi.getEtatVie().toString());
                tempsLabel.setText(String.valueOf(tamagotchi.getTempsDepuisDerniereAction()));
                propreteLabel.setText(String.valueOf(tamagotchi.getPropreteEnvironnement()));

                if (tamagotchi.isMalade()) {
                    mainLayout.setStyle("-fx-background-color: red;");
                    bonheurLabel.setTextFill(Color.WHITE);
                    etatLabel.setTextFill(Color.WHITE);
                    tempsLabel.setTextFill(Color.WHITE);
                    propreteLabel.setTextFill(Color.WHITE);
                } else {
                    mainLayout.setStyle(""); // Réinitialise la couleur par défaut
                    bonheurLabel.setTextFill(Color.BLACK);
                    etatLabel.setTextFill(Color.BLACK);
                    tempsLabel.setTextFill(Color.BLACK);
                    propreteLabel.setTextFill(Color.BLACK);
                }
            });
        });

        // Création de la scène
        Scene scene = new Scene(mainLayout, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            if (useCommandLine) {
                Platform.exit();
                System.exit(0);
            } else {
                e.consume();
            }
        });
    }
}
