package com.example.parqueadero;

public class MotoHibrida extends Vehiculo{
    private int velocidadMaxima;

    public MotoHibrida(String propietario, String placa, int velocidadMaxima) {
        super(propietario, placa);
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }
}
