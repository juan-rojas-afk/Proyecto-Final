package com.example.parqueadero;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaSalir {

    public void mostrar(Stage primaryStage) {
        primaryStage.setTitle("Salir");

        Label lblMensaje = new Label("¡Gracias por utilizar el sistemas del oarqueadero!");
         Button btnCerrar = new Button("Cerrar");
         btnCerrar.setOnAction(event -> primaryStage.close());

         VBox layout = new VBox(10);
         layout.getChildren().addAll(lblMensaje, btnCerrar);
         layout.setPadding(new Insets(10));

         Scene scene = new Scene(layout, 300, 200);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
}
