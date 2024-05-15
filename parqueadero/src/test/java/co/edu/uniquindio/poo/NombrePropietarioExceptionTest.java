package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NombrePropietarioExceptionTest {
    
    @Test
    public void testMensajePersonalizado() {
        // Crear una instancia de la excepción con un mensaje personalizado
        String mensajePersonalizado = "El nombre del propietario contiene caracteres no válidos.";
        NombrePropietarioException exception = new NombrePropietarioException(mensajePersonalizado);

        // Verificar que el mensaje de la excepción sea el esperado
        assertEquals(mensajePersonalizado, exception.getMessage(), "El mensaje de la excepción es incorrecto");
    }
}
