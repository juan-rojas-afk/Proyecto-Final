package co.edu.uniquindio.poo;

public class FormatoPlacaException extends ParqueaderoException {
    public FormatoPlacaException(String mensaje) {
        super(mensaje);
    }

    @Override
    public String obtenerTipo() {
        return "Error formato de placa";
    }
}
