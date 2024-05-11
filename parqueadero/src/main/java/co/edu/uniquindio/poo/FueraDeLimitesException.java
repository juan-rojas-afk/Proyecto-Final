package co.edu.uniquindio.poo;

public class FueraDeLimitesException extends ParqueaderoException {
    public FueraDeLimitesException(String mensaje) {
        super(mensaje);
    }

    @Override
    public String obtenerTipo() {
        return "Error en los límies ingresados";
    }
    
}
