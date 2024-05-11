package co.edu.uniquindio.poo;

public class NombrePropietarioException extends ParqueaderoException {
    public NombrePropietarioException(String mensaje) {
        super(mensaje);
    }
    
    @Override
    public String obtenerTipo() {
        return "Error en el nombre del propietario";
    }
}
