package com.example.parqueadero;

public abstract class Vehiculo {
    private String placa;
    private String propietario;

    public Vehiculo (String placa, String propietario) {
        this.placa = placa;
        this.propietario= propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
