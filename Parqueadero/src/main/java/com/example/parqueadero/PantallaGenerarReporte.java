package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaGenerarReporte {
    private Parqueadero parqueadero;

    public PantallaGenerarReporte(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public PantallaGenerarReporte() {
        this.parqueadero = null;
    }

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Generar Reporte Diario");

        String reporte = parqueadero.generarReporteDiario();
        Label lblReporte = new Label(reporte);

        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblReporte, btnVolver);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
