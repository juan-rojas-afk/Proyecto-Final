package com.example.parqueadero;

public class PuestoEstacionamiento {
    private int fila;
    private int columna;
    private boolean ocupado;
    private Vehiculo vehiculo;

    public PuestoEstacionamiento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupado = false;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void liberar() {
        this.ocupado = false;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
}
