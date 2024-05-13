package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;

public class PantallaConfigurarParqueadero {

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Configurar Parqeuadero");

        Label lblInfo = new Label("Intrese el tamaño del parqueadero: ");

        Label lblFilas = new Label("Número de filas: ");
        TextField txtFilas = new TextField();

        Label lblColumnas = new Label("Número de columnas: ");
        TextField txtColumnas = new TextField();

        Button btnGuardar = new Button("Guardar");
        Button btnVolver = new Button("Volver al menú princiapl");

        btnGuardar.setOnAction(event -> {
            try {
                int filas = Integer.parseInt(txtFilas.getText());
                int columnas = Integer.parseInt(txtColumnas.getText());

                if (filas <= 0 || columnas <= 0) {
                    System.out.println("Error: el número de filas y columnas debe ser mayor que cero.");
                    return;
                }
                System.out.println("El tamaño del parqueadero se ha guardado correctamente.");

                guardarConfiguracion(filas, columnas);

                primaryStage.close();
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar valores númericos válidos para filas y columnas");
            }
        });

        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblInfo, lblFilas, txtFilas, lblColumnas, txtColumnas, btnGuardar, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void guardarConfiguracion(int filas, int columnas) {
        try {
            FileWriter writer = new FileWriter("configuration.txt");

            writer.write("Filas: " + filas + "\n");
            writer.write("Columnas: " + columnas + "\n");

            writer.close();
            System.out.println("Configuración guardada correctamente en el archivo ´configuration.txt´");
        } catch (IOException e) {
            System.out.println("Error al guardar la configuración: " + e.getMessage());
        }
    }
}
