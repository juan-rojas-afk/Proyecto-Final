package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    protected String placa;
    protected String modelo;
    protected String propietario;

    public Vehiculo (String placa, String modelo, String propietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario= propietario;
    }

    public String getPlaca(){
        return placa;
    }

    public String getModelo(){
        return modelo;
    }

    public String getPropietario(){
        return propietario;
    }

    protected abstract int getTipo();
}


