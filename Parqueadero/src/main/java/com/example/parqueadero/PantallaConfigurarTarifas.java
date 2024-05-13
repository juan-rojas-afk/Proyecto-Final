package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PantallaConfigurarTarifas  {

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Configurar Tarifas");;

        Label lblCarroHora = new Label("Precio por hora para carros: ");
        TextField txtTarifaCarroHora = new TextField();
        Label lblCarroDia = new Label("Precio por día para carros: ");
        TextField txtTarifaCarroDia = new TextField();

        Label lblMotoClasicaHora = new Label("Precio por hora para motos clásicas: ");
        TextField txtTarifaMotoClasicaHora = new TextField();
        Label lblMotoClasicaDia = new Label("Precio po día para moto clásicas: ");
        TextField txtTarfifaMotoClasicaDia = new TextField();

        Label lblMotoHibridaHora = new Label("Precio por hora para motos híbridas: ");
        TextField txtTarifaMotoHibridaHora = new TextField();
        Label lblMotoHibridaDia = new Label("Precio por día pata motos híbridas: ");
        TextField txtTarifaMotoHibridaDia = new TextField();

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarTarifas(txtTarifaCarroHora.getText(), txtTarifaCarroDia.getText(),
                                                        txtTarifaMotoClasicaHora.getText(), txtTarfifaMotoClasicaDia.getText(),
                                                        txtTarifaMotoHibridaHora.getText(), txtTarifaMotoHibridaDia.getText()));


        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(event -> {
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.mostrar(primaryStage);
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        grid.add(lblCarroHora, 0, 0);
        grid.add(txtTarifaCarroHora, 1, 0);
        grid.add(lblCarroDia, 0, 1);
        grid.add(txtTarifaCarroDia, 1, 1);

        grid.add(lblMotoClasicaHora, 0, 2);
        grid.add(txtTarifaMotoClasicaHora, 1, 2);
        grid.add(lblMotoClasicaDia, 0, 3);
        grid.add(txtTarfifaMotoClasicaDia, 1, 3);

        grid.add(lblMotoHibridaHora, 0, 4);
        grid.add(txtTarifaMotoHibridaHora, 1, 4);
        grid.add(lblMotoHibridaDia, 0, 5);
        grid.add(txtTarifaMotoHibridaDia, 1, 5);

        grid.add(btnGuardar, 0, 6);
        grid.add(btnVolver, 1, 6);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void guardarTarifas(String tarifaCarroHora, String tarifaCarroDia, String tarifaMotoClasicaHora,
                                String tarifaMotoClasicaDia, String tarifaMotoHibridaHora, String tarifaMotoHibridaDia) {
        try {
            double carroHora = Double.parseDouble(tarifaCarroHora);
            double carroDia = Double.parseDouble(tarifaCarroDia);

            double motoclasicaHora = Double.parseDouble(tarifaMotoClasicaHora);
            double motoclasicaDia = Double.parseDouble(tarifaMotoClasicaDia);

            double motoHibridaHora = Double.parseDouble(tarifaMotoHibridaHora);
            double motoHibridaDia = Double.parseDouble(tarifaMotoHibridaDia);

            System.out.println("Las tarifas se han guardado correctamente.");
            System.out.println("Carro - Precio por hora: " + carroHora + ", Precio por día: " + carroDia);
            System.out.println("Motos clásicas - Precio por hora: " + motoclasicaHora + ", Precio por día: " + motoclasicaDia);
            System.out.println("Motos Híbridas - Precio por hora. " + motoHibridaHora + ", Precio por día: " + motoHibridaDia);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar valores númericos válidos.");
        }
    }
}
