package com.example.parqueadero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Bienvenida extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bienvenidos al sistema del parqueadero");

        Button btnComenzar = new Button();
        btnComenzar.setText("Comenzar");
        btnComenzar.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        StackPane root = new StackPane();
        root.getChildren().add(btnComenzar);
        primaryStage.setScene(new Scene(root, 300, 250));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
