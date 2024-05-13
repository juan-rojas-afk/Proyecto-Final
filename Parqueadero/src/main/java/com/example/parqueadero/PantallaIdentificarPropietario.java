package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaIdentificarPropietario {
    private Parqueadero parqueadero;

    public PantallaIdentificarPropietario(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public PantallaIdentificarPropietario() {
        this.parqueadero = null;
    }

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Identificar Propietario del Vehículo");

        Label lblPlaca = new Label("Ingrese la placa del vehículo: ");
        TextField txtPlaca = new TextField();

        Button btnIdentificar = new Button("Identificar");
        btnIdentificar.setOnAction(event -> {
            String placa = txtPlaca.getText();
            String propietario = parqueadero.identificarPropietario(placa);
            if (propietario != null) {
                System.out.println("El propietario del vehículo con la placa " + placa + " es: " + propietario);
            } else {
                System.out.println("No se encontró ningún vehículo con la placa especificada-");
            }
        });

        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblPlaca, txtPlaca, btnIdentificar, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
