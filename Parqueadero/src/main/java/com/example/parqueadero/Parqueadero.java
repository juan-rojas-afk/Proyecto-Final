package com.example.parqueadero;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    private PuestoEstacionamiento[][] puestos;
    private Map<String, String> vehiculosPlacaPropietario;

    public static final Integer TIPO_CARRO = 1;
    public static final Integer TIPO_MOTO_CLASICA = 2;
    public static final Integer TIPO_MOTO_HIBRIDA = 3;

    public Parqueadero(int filas, int columnas) {
        puestos = new PuestoEstacionamiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puestos[i][j] = new PuestoEstacionamiento(i, j);
            }
        }
        vehiculosPlacaPropietario = new HashMap<>();
    }

    public boolean puestoEstaLibre(int fila, int columna) {
        if (fila < 0 || fila >= puestos.length || columna < 0 || columna >= puestos[0].length) {
            return false;
        }
        return !puestos[fila][columna]. isOcupado();
    }

    public void estacionarVehiculoEnPuesto(int fila, int columna, String propietario, String placa, String tipoVehiculo) {
        puestos[fila][columna].ocupar();
    }

    public void desocuparPuesto(int fila, int columna) {
        if ( fila >= 0 && fila < puestos.length && columna >= 0 && columna < puestos[0].length) {
            puestos[fila][columna].liberar();
        }
    }

    public String identificarPropietario(String placa) {
        return vehiculosPlacaPropietario.get(placa);
    }

    public String generarReporteDiario() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte Diario del Parqueadero:\n");
        reporte.append("-----------------------------------\n");

        int carrosEstacionados = 0;
        int motosClasicasEstacionadas = 0;
        int motosHibridasEstacionadas = 0;

        for (PuestoEstacionamiento[] fila : puestos) {
            for (PuestoEstacionamiento puesto : fila) {
                if (puesto.isOcupado()) {
                    Vehiculo vehiculo = puesto.getVehiculo();
                    if (vehiculo instanceof Carro) {
                        carrosEstacionados++;
                    } else if (vehiculo instanceof  MotoClasica) {
                        motosClasicasEstacionadas++;
                    } else if (vehiculo instanceof MotoHibrida) {
                        motosHibridasEstacionadas++;
                    }
                }
            }
        }

        reporte.append("Total de carros Estacionados: ").append(carrosEstacionados).append("\n");
        reporte.append("Total de Motos Clásicas Estacionadas: ").append(motosClasicasEstacionadas).append("\n");
        reporte.append("Total de Motos Híbrida Estacionadas: ").append(motosHibridasEstacionadas).append("\n");

        return reporte.toString();
    }

    // Método obtener estado del parqueadero
    public String[][] obtenerEstadoParqueadero() {
        String[][] estadoParqueadero = new String[puestos.length][puestos[0].length];
        for (int fila = 0; fila < puestos.length; fila++) {
            for (int columna = 0; columna < puestos[fila].length; columna++) {
                if (puestos[fila][columna].isOcupado()) {
                    estadoParqueadero[fila][columna] = "Ocupado";
                } else {
                    estadoParqueadero[fila][columna] = "Libre";
                }
            }
        }
        return estadoParqueadero;
    }

    public void estacionarVehiculoEnPuesto(int f, int c) {

    }
}
