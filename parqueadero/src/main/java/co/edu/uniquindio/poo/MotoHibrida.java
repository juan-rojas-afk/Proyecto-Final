package co.edu.uniquindio.poo;

public class MotoHibrida extends Vehiculo{
    private int velocidadMaxima; // La velocidad máxima de la moto.

    /**
     * Constructor de la clase Moto.
     * @param placa La placa de la moto.
     * @param modelo El modelo de la moto.
     * @param propietario El propietario de la moto.
     * @param velocidadMaxima La velocidad máxima de la moto.
     */
    public MotoHibrida(String placa, String modelo, String propietario, int velocidadMaxima) {
        super(placa, modelo, propietario);
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * Método para obtener el tipo de vehículo.
     * @return El tipo de vehículo, en este caso, el tipo de moto.
     */
    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    @Override
    public int getTipo() {
        return Parqueadero.TIPO_MOTO_HIBRIDA; // Se retorna el tipo de moto, en este caso, moto Híbrida.
    }
    

}