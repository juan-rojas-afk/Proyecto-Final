package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class FueraDeLimiteExceptionTest {

    @Test
    public void testMensajePersonalizado() {
        // Crear una instancia de la excepción con un mensaje personalizado
        String mensajePersonalizado = "La posición (-1, 5) está fuera de los límites.";
        FueraDeLimitesException exception = new FueraDeLimitesException(mensajePersonalizado);

        // Verificar que el mensaje de la excepción sea el esperado
        assertEquals(mensajePersonalizado, exception.getMessage(), "El mensaje de la excepción es incorrecto");
    }
    
}
