package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaEstadoParqueadero {
    private  Parqueadero parqueadero;

    public PantallaEstadoParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public PantallaEstadoParqueadero() {
        this.parqueadero = null;
    }

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Estado del Parqueadero");

        // Obtener el estado actual del parqueadero
        String[][] estadoParqueadero = parqueadero.obtenerEstadoParqueadero();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setHgap(10);

        // Rellenar la cuadricula con el estado el parqeuadero
        for (int fila = 0; fila < estadoParqueadero.length; fila++) {
            for (int columna = 0; columna < estadoParqueadero[fila].length; columna++) {
                Label label = new Label(estadoParqueadero[fila][columna]);
                gridPane.add(label, columna, fila);
            }
        }

        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(gridPane, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
