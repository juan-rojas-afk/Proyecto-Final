package co.edu.uniquindio.poo;

/**
 * Clase abstracta que representa una excepción relacionada con el parqueadero.
 */
public abstract class ParqueaderoException extends Exception {

    /**
     * Constructor de la clase ParqueaderoException.
     * @param mensaje El mensaje de la excepción.
     */
    public ParqueaderoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Método abstracto para obtener el tipo de la excepción.
     * @return El tipo de la excepción.
     */
    public abstract String obtenerTipo();
}
