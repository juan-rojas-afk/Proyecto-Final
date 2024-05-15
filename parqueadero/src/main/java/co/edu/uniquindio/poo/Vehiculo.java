package co.edu.uniquindio.poo;

/**
 * Clase abstracta que representa un vehículo 
 */

public abstract class Vehiculo {
    protected String placa; // Placa del vehículo
    protected String modelo; // Modelo del vehículo
    protected String propietario;// Nombre del propietario del vehículo

    /**
     * Constructor de la clase Vehiculo.
     * @param placa La placa del vehículo.
     * @param modelo El modelo del vehículo.
     * @param propietario El nombre del propietario del vehículo.
     */
    public Vehiculo (String placa, String modelo, String propietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario= propietario;
    }

     /**
     * Método para obtener la placa del vehículo.
     * @return La placa del vehículo.
     */
    public String getPlaca(){
        return placa;
    }

    /**
     * Método para obtener el modelo del vehículo.
     * @return El modelo del vehículo.
     */
    public String getModelo(){
        return modelo;
    }

    /**
     * Método para obtener el nombre del propietario del vehículo.
     * @return El nombre del propietario del vehículo.
     */
    public String getPropietario(){
        return propietario;
    }

    /**
     * Método abstracto para obtener el tipo de vehículo.
     * @return El tipo de vehículo.
     */
    protected abstract int getTipo();
}

