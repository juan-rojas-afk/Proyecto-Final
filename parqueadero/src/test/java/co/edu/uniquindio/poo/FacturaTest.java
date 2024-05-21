package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {

    @Test
    public void testImprimirFactura() {
        // Arrange: Crear un vehículo y una factura
        Vehiculo vehiculo = new Carro("ABC123", "Toyota", "Propietario");
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 5, 21, 10, 0, 0); // Hora de entrada de ejemplo
        LocalDateTime horaSalida = LocalDateTime.of(2024, 5, 21, 12, 0, 0); // Hora de salida de ejemplo
        double costoTotal = 1000.0; // Costo total de ejemplo
        Factura factura = new Factura(vehiculo, horaEntrada, horaSalida, costoTotal);

        // Act: Redirigir la salida estándar a un PrintStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Guardar el PrintStream original
        System.setOut(new PrintStream(outputStream));

        // Act: Llamar al método imprimirFactura
        factura.imprimirFactura();

        // Act: Restaurar la salida estándar
        System.setOut(originalOut);

        // Act: Obtener la salida capturada
        String printedOutput = outputStream.toString();

        // Assert: Verificar que la salida contiene la información esperada
        assertTrue(printedOutput.contains("Factura"));
        assertTrue(printedOutput.contains("Vehículo: 1")); // Suponiendo que getTipo() devuelve el tipo de vehículo
        assertTrue(printedOutput.contains("Placa: ABC123"));
        assertTrue(printedOutput.contains("Modelo: Toyota"));
        assertTrue(printedOutput.contains("Propietario: Propietario"));
        assertTrue(printedOutput.contains("Hora de entrada: 2024-05-21 10:00:00"));
        assertTrue(printedOutput.contains("Hora de salida: 2024-05-21 12:00:00"));
        assertTrue(printedOutput.contains("Costo total : $1000.0"));
    }
}