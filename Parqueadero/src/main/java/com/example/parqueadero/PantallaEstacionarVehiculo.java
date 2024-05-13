package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaEstacionarVehiculo {
    private final int filasParqueadero;
    private final int columnasParqueadero;
    private final Parqueadero parqueadero;

    private TextField txtPropietario;
    private TextField txtPlaca;
    private TextField txtTipoVehiculo;

    public PantallaEstacionarVehiculo(int filas, int columnas, Parqueadero parqueadero) {
        this.filasParqueadero = filas;
        this.columnasParqueadero = columnas;
        this.parqueadero = parqueadero;
    }

    public PantallaEstacionarVehiculo() {
        this.filasParqueadero = 0;
        this.columnasParqueadero = 0;
        this.parqueadero = null;
    }

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Estacionar Vehículo");

        Label lblPropietario = new Label("Nombre del propietario: ");
        TextField txtPropietario = new TextField();

        Label lblPlaca = new Label("Placa del vehículo: ");
        TextField txtPlaca = new TextField();

        Label lblTipoVehiculo = new Label("Tipo de vehículo: ");
        TextField txtTipoVehiculo = new TextField();

        Button btnContinuar = new Button("Continuar");
        btnContinuar.setOnAction(event -> {
            if (validarDatos()) {
                mostrarSeleccionarPuesto(primaryStage);
            } else {
                mostrarMensaje("Por favor, complete todos los campos.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblPropietario, txtPropietario, lblPlaca, txtPlaca, lblTipoVehiculo, txtTipoVehiculo, btnContinuar);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validarDatos() {
        return !txtPropietario.getText().isEmpty() && !txtPlaca.getText().isEmpty() && !txtTipoVehiculo.getText().isEmpty();
    }

    private void mostrarSeleccionarPuesto(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        // Crear ña cuadrícula para representar el parqueadero
        for (int fila = 0; fila < filasParqueadero; fila++) {
            for (int columna = 0; columna < columnasParqueadero; columna++) {
                Button btnPuesto = new Button("(" + fila + ", " + columna + ")");
                int finalFila = fila;
                int finalColumna = columna;
                btnPuesto.setOnAction(event -> {
                    // Acción a realizar cuando se hace clic en un puesto
                    String propietario = txtPropietario.getText();
                    String placa = txtPlaca.getText();
                    String tipoVehiculo = txtTipoVehiculo.getText();

                    if (parqueadero.puestoEstaLibre(finalFila, finalColumna)) {
                        parqueadero.estacionarVehiculoEnPuesto(finalFila, finalColumna, propietario, placa, tipoVehiculo);
                        mostrarMensaje("Vehículo estacionado correctamente en fila " + (finalFila + 1) + ", columna " + (finalColumna + 1));
                    } else {
                        mostrarMensaje("El puesto seleccionado ya está ocupado.");
                    }
                });
                grid.add(btnPuesto, columna, fila);
            }
        }

        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}


