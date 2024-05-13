package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PantallaSeleccionarPuesto {
    private int filasParqueadero;
    private int columnasParqueadero;
    private Parqueadero parqueadero;

    public PantallaSeleccionarPuesto(int filas, int columnas, Parqueadero parqueadero) {
        this.filasParqueadero = filas;
        this.columnasParqueadero = columnas;
        this.parqueadero = parqueadero;
    }

    public PuestoEstacionamiento mostrar(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleccionar Puesto");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        final PuestoEstacionamiento[] puestoSeleccionado = {null};

        for (int fila = 0; fila < filasParqueadero; fila++) {
            for (int columna = 0; columna < columnasParqueadero; columna++) {
                final int f = fila;
                final int c = columna;
                Button button = new Button("Puesto " + (fila + 1) + "-" + (columna + 1));
                button.setOnAction(event -> {
                    if (!parqueadero.puestoEstaLibre(f, c)) {
                        mostrarMensaje("El puesto seleccionado ya está ocupado.");
                    } else {
                        puestoSeleccionado[0] = new PuestoEstacionamiento(f, c);
                        parqueadero.estacionarVehiculoEnPuesto(f, c);
                        stage.close();
                    }
                });
                gridPane.add(button, columna, fila);
            }

        }
        StackPane root = new StackPane(gridPane);
        Scene scene = new Scene((root));
        stage.setScene(scene);
        stage.showAndWait();

        return null;
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
