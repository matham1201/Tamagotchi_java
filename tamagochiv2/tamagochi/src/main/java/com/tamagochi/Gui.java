package com.tamagochi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {
    private Tamagotchi tamagotchi;
    private Label happinessLabel;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        tamagotchi = new Tamagotchi();

        Label titleLabel = new Label("Tamagotchi");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label happinessTitleLabel = new Label("Bonheur:");
        happinessLabel = new Label(String.valueOf(tamagotchi.getHappiness()));

        Label statusTitleLabel = new Label("État:");
        statusLabel = new Label(tamagotchi.getState().name());


        Button playButton = new Button("Jouer");
        playButton.setOnAction(e -> play());

        Button feedButton = new Button("Nourrir");
        feedButton.setOnAction(e -> feed());

        Button cleanButton = new Button("Nettoyer");
        cleanButton.setOnAction(e -> clean());

        Button passTimeButton = new Button("Passer du temps");
        passTimeButton.setOnAction(e -> passTime());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                titleLabel,
                happinessTitleLabel, happinessLabel,
                statusTitleLabel, statusLabel,
                playButton, feedButton, cleanButton, passTimeButton
        );

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void play() {
        tamagotchi.play();
        updateStatus();
    }

    private void feed() {
        tamagotchi.feed();
        updateStatus();
    }

    private void clean() {
        tamagotchi.clean();
        updateStatus();
    }

    private void passTime() {
        tamagotchi.passTime();
        updateStatus();
    }

    private void updateStatus() {
        happinessLabel.setText(String.valueOf(tamagotchi.getHappiness()));
        statusLabel.setText(tamagotchi.getState().toString());
    }
}
