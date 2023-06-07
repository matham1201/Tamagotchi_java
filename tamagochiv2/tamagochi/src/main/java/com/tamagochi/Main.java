package com.tamagochi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Tamagotchi tamagotchi;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        tamagotchi = new Tamagotchi();

        Label tamagotchiStatusLabel = new Label();
        tamagotchiStatusLabel.setText(tamagotchi.getStatus());

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            tamagotchi.play();
            tamagotchiStatusLabel.setText(tamagotchi.getStatus());
        });

        Button feedButton = new Button("Feed");
        feedButton.setOnAction(e -> {
            tamagotchi.feed();
            tamagotchiStatusLabel.setText(tamagotchi.getStatus());
        });

        Button cleanButton = new Button("Clean");
        cleanButton.setOnAction(e -> {
            tamagotchi.clean();
            tamagotchiStatusLabel.setText(tamagotchi.getStatus());
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(tamagotchiStatusLabel, playButton, feedButton, cleanButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Tamagotchi App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
