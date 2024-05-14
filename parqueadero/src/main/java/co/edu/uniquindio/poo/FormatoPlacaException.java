package co.edu.uniquindio.poo;

/**
 * Excepción lanzada cuando se detecta un error en el formato de la placa del vehículo.
 */
public class FormatoPlacaException extends ParqueaderoException {

    /**
     * Constructor de la excepción.
     * @param mensaje El mensaje de error asociado a la excepción.
     */
    public FormatoPlacaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Método para obtener el tipo de error.
     * @return El tipo de error, en este caso, "Error formato de placa".
     */
    @Override
    public String obtenerTipo() {
        return "Error formato de placa";
    }
}
