package co.edu.uniquindio.poo;

/**
 * Clase que representa una excepción lanzada cuando el nombre del propietario de un vehículo no cumple con el formato esperado.
 */
public class NombrePropietarioException extends ParqueaderoException {

    /**
     * Constructor de la clase NombrePropietarioException.
     * @param mensaje El mensaje de la excepción.
     */
    public NombrePropietarioException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Método para obtener el tipo de la excepción.
     * @return El tipo de la excepción.
     */
    @Override
    public String obtenerTipo() {
        return "Error en el nombre del propietario";
    }
}
