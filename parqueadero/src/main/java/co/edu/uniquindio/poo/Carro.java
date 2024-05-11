package co.edu.uniquindio.poo;

public class Carro extends Vehiculo{
    public Carro(String placa, String modelo, String propietario) {
        super(placa, modelo, propietario);
    }

    @Override
    public int getTipo() {
        return Parqueadero.TIPO_CARRO;
    }
    
}
