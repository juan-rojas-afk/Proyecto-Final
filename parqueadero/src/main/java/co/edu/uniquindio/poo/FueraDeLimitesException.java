package co.edu.uniquindio.poo;

/**
 * Excepción lanzada cuando se intenta acceder a una posición fuera de los límites del parqueadero.
 */
public class FueraDeLimitesException extends ParqueaderoException {

    /**
     * Constructor de la excepción.
     * @param mensaje El mensaje de error asociado a la excepción.
     */
    public FueraDeLimitesException(String mensaje) {
        super(mensaje);
    }

    /**
     * Método para obtener el tipo de error.
     * @return El tipo de error, en este caso, "Error en los límites ingresados".
     */
    @Override
    public String obtenerTipo() {
        return "Error en los límies ingresados";
    }
}
