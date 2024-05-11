package co.edu.uniquindio.poo;

class Moto extends Vehiculo {
    private int velocidadMaxima;

    public Moto(String placa, String modelo, String propietario, int velocidadMaxima) {
        super(placa, modelo, propietario);
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    @Override
    public int getTipo() {
        return Parqueadero.TIPO_MOTO_CLASICA;
    }
    
}
