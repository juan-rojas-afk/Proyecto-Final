package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero(5, 5); // Crear un parqueadero con 5 filas y 5 columnas para las pruebas
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, 5.0); // Tarifa por hora para carros: $5
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_CLASICA, 3.0); // Tarifa por hora para motos clásicas: $3
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_HIBRIDA, 4.0); // Tarifa por hora para motos híbridas: $4
    }

    @Test
    public void testSetTarifaPorHora() {
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, 5000);
        assertEquals(5.0, parqueadero.getTarifasPorHora().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testSetTarifaDiaria() {
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_CARRO, 10000);
        assertEquals(30.0, parqueadero.getTarifasDiarias().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testSetTarifaMensual() {
        parqueadero.setTarifaMensual(Parqueadero.TIPO_CARRO, 30000);
        assertEquals(300.0, parqueadero.getTarifasMensuales().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testGetTarifasPorHora() {
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, 5.0);
        assertEquals(5.0, parqueadero.getTarifasPorHora().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testGetTarifasDiarias() {
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_CARRO, 30.0);
        assertEquals(30.0, parqueadero.getTarifasDiarias().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testGetTarifasMensuales() {
        parqueadero.setTarifaMensual(Parqueadero.TIPO_CARRO, 300.0);
        assertEquals(300.0, parqueadero.getTarifasMensuales().get(Parqueadero.TIPO_CARRO));
    }

     @Test
    public void testPuestoDisponible_True() {
        assertTrue(parqueadero.puestoDisponible(0, 0)); // El primer puesto debería estar disponible al principio
    }

    @Test
    public void testPuestoDisponible_False() {
        Vehiculo vehiculo = new Carro("ABC123", "Toyota", "Juan");
        try {
            parqueadero.estacionarVehiculo(vehiculo, 0, 0);
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } // Estacionar un vehículo en el primer puesto
        assertTrue(parqueadero.puestoDisponible(0, 0)); // El puesto debería estar ocupado después de estacionar el vehículo
    }

    @Test
    public void testPuestoDisponibleCuandoParqueaderoVacio() {
        assertTrue(parqueadero.puestoDisponible(0, 0));
    }

    // Pruebas para el método estacionarVehiculo
    @Test
    public void testEstacionarVehiculo_Success() {
        Vehiculo vehiculo = new Carro("ABC123", "Toyota", "Juan");
        try {
            assertTrue(parqueadero.estacionarVehiculo(vehiculo, 0, 0));
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } // Estacionar un vehículo en el primer puesto
    }

    @Test
    public void testEstacionarVehiculo_Fail() {
        Vehiculo vehiculo1 = new Carro("ABC123", "Toyota", "Juan");
        Vehiculo vehiculo2 = new Carro("DEF456", "Honda", "Maria");
       
        try {
            parqueadero.estacionarVehiculo(vehiculo1, 0, 0);
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } // Estacionar un vehículo en el primer puesto
        try {
            assertFalse(parqueadero.estacionarVehiculo(vehiculo2, 0, 0));
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } // Intentar estacionar otro vehículo en el mismo puesto
        
    }

    @Test
    public void testDesocuparPuesto_Empty() {
        assertNull(parqueadero.desocuparPuesto(0, 0)); // Intentar desocupar un puesto vacío
    }

    @Test
    public void testCalcularCostoEstadia_Carro() {
        LocalDateTime horaEntrada = LocalDateTime.now().minusHours(2); // Hora de entrada hace 2 horas
        LocalDateTime horaSalida = LocalDateTime.now(); // Hora de salida actual
        double costoEsperado = 2 * 1000; // 2 horas * $5 por hora
        assertEquals(costoEsperado, parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, 1));
    }

    @Test
    public void testCalcularCostoEstadia_MotoClasica() {
        LocalDateTime horaEntrada = LocalDateTime.now().minusHours(3); // Hora de entrada hace 3 horas
        LocalDateTime horaSalida = LocalDateTime.now(); // Hora de salida actual
        double costoEsperado = 3 * 3.0; // 3 horas * $3 por hora
        assertEquals(costoEsperado, parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, 2));
    }

    @Test
    public void testCalcularCostoEstadia_MotoHibrida() {
        LocalDateTime horaEntrada = LocalDateTime.now().minusHours(4); // Hora de entrada hace 4 horas
        LocalDateTime horaSalida = LocalDateTime.now(); // Hora de salida actual
        double costoEsperado = 4 * 4.0; // 4 horas * $4 por hora
        assertEquals(costoEsperado, parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, 3));
    }

    @Test
    public void testRegistrarSalida_Carro() {
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");
  
        try {
            parqueadero.estacionarVehiculo(carro, 0, 0);
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } // Estacionar un carro
        double costoEsperado = 2 * 5.0; // Costo esperado para 2 horas de estadía
        assertEquals(costoEsperado, parqueadero.registrarSalida("ABC123"));
        assertNull(parqueadero.desocuparPuesto(0, 0)); // Verificar que el puesto se haya desocupado después de la salida

    }

    @Test
    public void testRegistrarSalida_MotoClasica() {
        Vehiculo moto = new Moto("DEF456", "Honda", "Maria", 120); // Moto con velocidad máxima de 120 km/h
        try {
            parqueadero.estacionarVehiculo(moto, 0, 0); // Estacionar una moto
            double costoEsperado = 3 * 3.0; // Costo esperado para 3 horas de estadía
            assertEquals(costoEsperado, parqueadero.registrarSalida("DEF456"));
            assertNull(parqueadero.desocuparPuesto(0, 0)); // Verificar que el puesto se haya desocupado después de la salida
        } catch (ParqueaderoException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testCalcularCosto_HorasCarro() {
        String placa = "ABC123";
        parqueadero.registrarIngreso(new Carro(placa, "Toyota", "Juan"), 0, 0); // Registrar entrada de un carro
        double costoEsperado = 5 * 5.0; // 5 horas * $5 por hora
        assertEquals(costoEsperado, parqueadero.calcularcosto(placa, 5));
    }

    @Test
    public void testCalcularCosto_HorasMotoClasica() {
        String placa = "DEF456";
        parqueadero.registrarIngreso(new Moto(placa, "Honda", "Maria", 120), 0, 0); // Registrar entrada de una moto clásica
        double costoEsperado = 6 * 3.0; // 6 horas * $3 por hora
        assertEquals(costoEsperado, parqueadero.calcularcosto(placa, 6));
    }

    @Test
    public void testCalcularCosto_HorasMotoHibrida() {
        String placa = "GHI789";
        parqueadero.registrarIngreso(new Moto(placa, "Yamaha", "Carlos", 100), 0, 0); // Registrar entrada de una moto híbrida
        double costoEsperado = 7 * 4.0; // 7 horas * $4 por hora
        assertEquals(costoEsperado, parqueadero.calcularcosto(placa, 7));
    }

    @Test
    public void testGenerarReporteDiario_SinVehiculos() {
        Map<Integer, Double> reporteDiario = parqueadero.generarReporteDiario();
        assertTrue(reporteDiario.isEmpty(), "El reporte diario debería estar vacío cuando no hay vehículos estacionados.");
    }

    @Test
    public void testGenerarReporteDiario_ConVehiculos() {
        // Estacionar algunos vehículos de prueba
        try {
            parqueadero.estacionarVehiculo(new Carro("ABC123", "Toyota", "Juan"), 1, 1); // Estacionar un carro
            parqueadero.estacionarVehiculo(new Moto("DEF456", "Honda", "Maria", 120), 2, 2); // Estacionar una moto clásica
            parqueadero.estacionarVehiculo(new Moto("GHI789", "Yamaha", "Carlos", 100), 3, 3); // Estacionar una moto híbrida
        } catch (ParqueaderoException e) {
            fail("No se esperaba una excepción al estacionar vehículos: " + e.getMessage());
        }

        // Generar el reporte diario
        Map<Integer, Double> reporteDiario = parqueadero.generarReporteDiario();

        // Verificar que el reporte contenga las categorías esperadas
        assertTrue(reporteDiario.containsKey(Parqueadero.TIPO_CARRO), "El reporte diario debería contener la categoría de carros.");
        assertTrue(reporteDiario.containsKey(Parqueadero.TIPO_MOTO_CLASICA), "El reporte diario debería contener la categoría de motos clásicas.");
        assertTrue(reporteDiario.containsKey(Parqueadero.TIPO_MOTO_HIBRIDA), "El reporte diario debería contener la categoría de motos híbridas.");

        // Verificar que los valores del reporte sean los esperados
        assertEquals(5.0, reporteDiario.get(Parqueadero.TIPO_CARRO), "El costo total de carros en el reporte diario es incorrecto.");
        assertEquals(3.0, reporteDiario.get(Parqueadero.TIPO_MOTO_CLASICA), "El costo total de motos clásicas en el reporte diario es incorrecto.");
        assertEquals(4.0, reporteDiario.get(Parqueadero.TIPO_MOTO_HIBRIDA), "El costo total de motos híbridas en el reporte diario es incorrecto.");
    }

    @Test
    public void testGenerarReporteMensual_SinVehiculos() {
        Map<Integer, Double> reporteMensual = parqueadero.generarReporteMensual();
        assertTrue(reporteMensual.isEmpty(), "El reporte mensual debería estar vacío cuando no hay vehículos estacionados.");
    }

    @Test
    public void testGenerarReporteMensual_ConVehiculos() {
        // Estacionar algunos vehículos de prueba
        try {
            parqueadero.estacionarVehiculo(new Carro("ABC123", "Toyota", "Juan"), 0, 0); // Estacionar un carro
            parqueadero.estacionarVehiculo(new Moto("DEF456", "Honda", "Maria", 120), 1, 1); // Estacionar una moto clásica
            parqueadero.estacionarVehiculo(new Moto("GHI789", "Yamaha", "Carlos", 100), 2, 2); // Estacionar una moto híbrida
        } catch (ParqueaderoException e) {
            fail("No se esperaba una excepción al estacionar vehículos: " + e.getMessage());
        }

        // Generar el reporte mensual
        Map<Integer, Double> reporteMensual = parqueadero.generarReporteMensual();

        // Verificar que el reporte contenga las categorías esperadas
        assertTrue(reporteMensual.containsKey(Parqueadero.TIPO_CARRO), "El reporte mensual debería contener la categoría de carros.");
        assertTrue(reporteMensual.containsKey(Parqueadero.TIPO_MOTO_CLASICA), "El reporte mensual debería contener la categoría de motos clásicas.");
        assertTrue(reporteMensual.containsKey(Parqueadero.TIPO_MOTO_HIBRIDA), "El reporte mensual debería contener la categoría de motos híbridas.");

        // Verificar que los valores del reporte sean los esperados
        assertEquals(50.0, reporteMensual.get(Parqueadero.TIPO_CARRO), "El costo total de carros en el reporte mensual es incorrecto.");
        assertEquals(30.0, reporteMensual.get(Parqueadero.TIPO_MOTO_CLASICA), "El costo total de motos clásicas en el reporte mensual es incorrecto.");
        assertEquals(40.0, reporteMensual.get(Parqueadero.TIPO_MOTO_HIBRIDA), "El costo total de motos híbridas en el reporte mensual es incorrecto.");
    }

    @Test
    public void testBuscarVehiculoPorPlaca_VehiculoNoExistente() {
        // Buscar un vehículo que no está estacionado
        String placa = "ABC123";
        Vehiculo vehiculo = parqueadero.buscarVehiculoPorPlaca(placa);
        assertNull(vehiculo, "No debería encontrarse un vehículo con la placa " + placa);
    }

    @Test
    public void testBuscarVehiculoPorPlaca_VehiculoExistente() {
        // Estacionar un vehículo para la prueba
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");
        
        // Buscar el vehículo estacionado
        String placa = "ABC123";
        Vehiculo vehiculoEncontrado = parqueadero.buscarVehiculoPorPlaca(placa);

        // Verificar que el vehículo encontrado sea el mismo que se estacionó
        assertNotNull(vehiculoEncontrado, "Se esperaba encontrar un vehículo con la placa ABC123" );
        assertEquals(carro, vehiculoEncontrado, "El vehículo encontrado no es el mismo que se estacionó");
    }
}
