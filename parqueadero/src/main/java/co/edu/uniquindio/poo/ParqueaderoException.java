package co.edu.uniquindio.poo;

public abstract class ParqueaderoException extends Exception {
    public ParqueaderoException(String mensaje) {
        super(mensaje);
    }

    public abstract String obtenerTipo();
}
