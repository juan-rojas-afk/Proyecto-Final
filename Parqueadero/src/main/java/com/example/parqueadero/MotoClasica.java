package com.example.parqueadero;

public class MotoClasica extends Vehiculo{
    private int velocidadMaxima;

    public MotoClasica(String pripietario, String placa, int velocidadMaxima) {
        super(pripietario, placa);
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

}
