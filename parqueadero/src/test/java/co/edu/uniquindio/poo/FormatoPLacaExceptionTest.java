package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class FormatoPLacaExceptionTest {

    @Test
    public void testMensajePersonalizado() {
        // Crear una instancia de la excepción con un mensaje personalizado
        String mensajePersonalizado = "El formato de la placa no cumple con ABC123.";
        FormatoPlacaException exception = new FormatoPlacaException(mensajePersonalizado);

        // Verificar que el mensaje de la excepción sea el esperado
        assertEquals(mensajePersonalizado, exception.getMessage(), "El mensaje de la excepción es incorrecto");
    }
    
}
