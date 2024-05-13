package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaDesocuparPuesto {
    private Parqueadero parqueadero;

    public PantallaDesocuparPuesto(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public PantallaDesocuparPuesto() {
        this.parqueadero = null;
    }

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Desocupar Puesto");

        Label lblFila = new Label("Número de fila del puesto: ");
        TextField txtFila = new TextField();
        Label lblColumna = new Label("Nímero de columna del puesto: ");
        TextField txtColumna = new TextField();
        Button btnVolver = new Button("Volver al menú princiapl");

        Button btnDesocupar = new Button("Desocupar");
        btnDesocupar.setOnAction(event -> {
            try {
                int fila = Integer.parseInt(txtFila.getText());
                int columna = Integer.parseInt(txtColumna.getText());

                if (parqueadero.puestoEstaLibre(fila, columna)) {
                    System.out.println("El puesto está libre.");
                } else {
                    parqueadero.desocuparPuesto(fila, columna);
                    System.out.println("Puesto desocupado exitosamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese valores válidos para fila y columna.");
            }
        });

        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblFila, txtFila, lblColumna, txtColumna, btnDesocupar, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
