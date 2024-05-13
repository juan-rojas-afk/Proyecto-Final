package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaPrincipal {

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Menú del Parqueadero");

        Button btnConfigurarTarifas = new Button("Configurar tarifas");
        Button btnConfigurarParqueadero = new Button("Configurar tamaño del Parqueadero");
        Button btnEstacionarVehiculo = new Button("Estacionar vehículo");
        Button btnDesocuparPuesto = new Button("Desocupar puesto");
        Button btnIdentificarpropietario = new Button("Identificar propietario del Vehiculo");
        Button btnGenerarReporte = new Button("generar resporte diario");
        Button btnEstadoParqueadero = new Button("Estado del Parqueadero");
        Button btnSalir = new Button("Salir");

        btnConfigurarTarifas.setOnAction(event -> {
            PantallaConfigurarTarifas pantalla = new PantallaConfigurarTarifas();
            pantalla.mostrar(primaryStage);
        });

        btnConfigurarParqueadero.setOnAction(event -> {
            PantallaConfigurarParqueadero pantalla = new PantallaConfigurarParqueadero();
            pantalla.mostrar(primaryStage);
        });

        btnEstacionarVehiculo.setOnAction(event -> {
            PantallaEstacionarVehiculo pantalla = new PantallaEstacionarVehiculo();
            pantalla.mostrar(primaryStage);
        });

        btnDesocuparPuesto.setOnAction(event -> {
            PantallaDesocuparPuesto pantalla = new PantallaDesocuparPuesto();
            pantalla.mostrar(primaryStage);
        });

        btnIdentificarpropietario.setOnAction(event -> {
            PantallaIdentificarPropietario pantalla = new PantallaIdentificarPropietario();
            pantalla.mostrar(primaryStage);
        });

        btnGenerarReporte.setOnAction(event -> {
            PantallaGenerarReporte pantalla = new PantallaGenerarReporte();
            pantalla.mostrar(primaryStage);
        });

        btnEstadoParqueadero.setOnAction(event -> {
            PantallaEstadoParqueadero pantalla = new PantallaEstadoParqueadero();
            pantalla.mostrar(primaryStage);
        });

        btnSalir.setOnAction(event -> {
            PantallaSalir pantalla = new PantallaSalir();
            pantalla.mostrar(primaryStage);
        });

        //Diseño de la interfaz
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                btnConfigurarTarifas,
                btnConfigurarParqueadero,
                btnEstacionarVehiculo,
                btnDesocuparPuesto,
                btnIdentificarpropietario,
                btnGenerarReporte,
                btnEstadoParqueadero,
                btnSalir
        );
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
