package com.tamagochi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.tamagochi.Tamagotchi.EtatVie;


public class Gui extends Application {
    private Tamagotchi tamagotchi;

    private Label bonheurLabel;
    private Label etatLabel;
    private Label tempsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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
                jouerButton, nourrirButton, nettoyerButton, soignerButton, incrementerTempsButton, afficherEtatButton
        );

        // Mise à jour des labels en fonction de l'état du Tamagotchi
        tamagotchi.setOnTamagotchiUpdate(() -> {
            Platform.runLater(() -> {
                bonheurLabel.setText(String.valueOf(tamagotchi.getBonheur()));
                etatLabel.setText(tamagotchi.getEtatVie().toString());
                tempsLabel.setText(String.valueOf(tamagotchi.getTempsDepuisDerniereAction()));
            });
        });

        // Création de la scène
        Scene scene = new Scene(mainLayout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        
    }
    

}
