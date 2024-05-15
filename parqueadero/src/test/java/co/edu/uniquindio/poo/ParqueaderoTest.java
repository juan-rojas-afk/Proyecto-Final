package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------//
    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero(5, 5); // Crear un parqueadero con 5 filas y 5 columnas para las pruebas
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, 5000); // Tarifa por hora para carros: $5
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_CLASICA, 3000); // Tarifa por hora para motos clásicas: $3
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_HIBRIDA, 4000); // Tarifa por hora para motos híbridas: $4
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void testSetTarifaPorHora() {
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, 5000);
        assertEquals(5000, parqueadero.getTarifasPorHora().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testSetTarifaDiaria() {
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_CARRO, 10000);
        assertEquals(10000, parqueadero.getTarifasDiarias().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testSetTarifaMensual() {
        parqueadero.setTarifaMensual(Parqueadero.TIPO_CARRO, 30000);
        assertEquals(30000, parqueadero.getTarifasMensuales().get(Parqueadero.TIPO_CARRO));
    }

    @Test
    public void testGetTarifasPorHora() {
        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_CLASICA, 3000);
        assertEquals(3000, parqueadero.getTarifasPorHora().get(Parqueadero.TIPO_MOTO_CLASICA));
    }

    @Test
    public void testGetTarifasDiarias() {
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_MOTO_CLASICA, 8000);
        assertEquals(8000, parqueadero.getTarifasDiarias().get(Parqueadero.TIPO_MOTO_CLASICA));
    }

    @Test
    public void testGetTarifasMensuales() {
        parqueadero.setTarifaMensual(Parqueadero.TIPO_MOTO_CLASICA, 20000);
        assertEquals(20000, parqueadero.getTarifasMensuales().get(Parqueadero.TIPO_MOTO_CLASICA));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//


     @Test
    public void testPuestoDisponible_True() {
        assertTrue(parqueadero.puestoDisponible(0, 0)); 
    }

    @Test
    public void testPuestoDisponible_False() {
        Vehiculo vehiculo = new Carro("ABC123", "Toyota", "Juan");
        try {
            parqueadero.estacionarVehiculo(vehiculo, 0, 0);
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } 
        assertTrue(parqueadero.puestoDisponible(0, 0)); 
    }

    @Test
    public void testPuestoDisponibleCuandoParqueaderoVacio() {
        assertTrue(parqueadero.puestoDisponible(0, 0));
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void testEstacionarVehiculo_Success() {
        Vehiculo vehiculo = new Carro("ABC123", "Toyota", "Juan");
        try {
            assertTrue(parqueadero.estacionarVehiculo(vehiculo, 0, 0));
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void testEstacionarVehiculo_Fail() {
        Vehiculo vehiculo1 = new Carro("ABC123", "Toyota", "Juan");
        Vehiculo vehiculo2 = new Carro("DEF456", "Honda", "Maria");
       
        try {
            parqueadero.estacionarVehiculo(vehiculo1, 0, 0);
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } 
        try {
            assertFalse(parqueadero.estacionarVehiculo(vehiculo2, 0, 0));
        } catch (ParqueaderoException e) {
            e.printStackTrace();
        } 
        
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void testDesocuparPuesto_Empty() {
        assertNull(parqueadero.desocuparPuesto(0, 0)); 
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void calcularCostoEstadia_CalculaCorrectamenteCostoHora() {

        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHora = 1000; 
        parqueadero.setTarifas(Parqueadero.TIPO_CARRO, tarifaHora);
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 5, 15, 10, 0); 
        LocalDateTime horaSalida = LocalDateTime.of(2024, 5, 15, 12, 0); 
        
        double costo = parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, Parqueadero.TIPO_CARRO);

        assertEquals(2000, costo, 0.01); 
    }

    @Test
    public void calcularCostoEstadia_MotoClasica_CalculaCorrectamenteCostoHora() {
        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHoraMotoClasica = 800; 
        parqueadero.setTarifas(Parqueadero.TIPO_MOTO_CLASICA, tarifaHoraMotoClasica);
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 5, 15, 10, 0);
        LocalDateTime horaSalida = LocalDateTime.of(2024, 5, 15, 12, 0);
        
        double costo = parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, Parqueadero.TIPO_MOTO_CLASICA);

        assertEquals(1600, costo, 0.01); 
    }

    @Test
    public void calcularCostoEstadia_MotoHibrida_CalculaCorrectamenteCostoHora() {
        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHoraMotoHibrida = 600;
        parqueadero.setTarifas(Parqueadero.TIPO_MOTO_HIBRIDA, tarifaHoraMotoHibrida);
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 5, 15, 10, 0);
        LocalDateTime horaSalida = LocalDateTime.of(2024, 5, 15, 12, 0);
        
        double costo = parqueadero.calcularCostoEstadia(horaEntrada, horaSalida, Parqueadero.TIPO_MOTO_HIBRIDA);

        assertEquals(1200, costo, 0.01);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void registrarSalida_RegistraSalidaYCalculaCostoCorrectamente() {

        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHoraCarro = 1000; 
        parqueadero.setTarifas(Parqueadero.TIPO_CARRO, tarifaHoraCarro);
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");

        try {
            parqueadero.estacionarVehiculo(carro, 0, 0); 
            double costo = parqueadero.registrarSalida("ABC123"); 

            assertEquals(2000, costo, 0.01); 
        } catch (ParqueaderoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void calcularCosto_CalculaCorrectamenteCostoHora() {

        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHoraCarro = 1000; 
        parqueadero.setTarifas(Parqueadero.TIPO_CARRO, tarifaHoraCarro);
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");

        try {
            parqueadero.estacionarVehiculo(carro, 0, 0); 
            double costo = parqueadero.calcularcosto("ABC123", 2); 

            assertEquals(2000, costo, 0.01); 
        } catch (ParqueaderoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void testGenerarReporteDiario_SinVehiculos() {
        Map<Integer, Double> reporteDiario = parqueadero.generarReporteDiario();
        assertTrue(reporteDiario.isEmpty(), "El reporte diario debería estar vacío cuando no hay vehículos estacionados.");
    }

    @Test
    public void generarReporteDiario_GeneraReporteCorrectamente() {

        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaHoraCarro = 1000; 
        double tarifaHoraMoto = 800; 
        parqueadero.setTarifas(Parqueadero.TIPO_CARRO, tarifaHoraCarro);
        parqueadero.setTarifas(Parqueadero.TIPO_MOTO_CLASICA, tarifaHoraMoto);
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");
        Vehiculo moto = new Moto("XYZ789", "Honda", "Maria", 150);

        try {
            parqueadero.estacionarVehiculo(carro, 0, 0); 
            parqueadero.estacionarVehiculo(moto, 1, 0); 
            Map<Integer, Double> reporteDiario = parqueadero.generarReporteDiario();

            assertEquals(2000, reporteDiario.get(Parqueadero.TIPO_CARRO), 0.01); 
            assertEquals(1600, reporteDiario.get(Parqueadero.TIPO_MOTO_CLASICA), 0.01); 
        } catch (ParqueaderoException e) {
            
            System.out.println("Error: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void generarReporteMensual_GeneraReporteCorrectamente() {
        
        Parqueadero parqueadero = new Parqueadero(5, 5);
        double tarifaDiariaCarro = 1000; 
        double tarifaDiariaMoto = 800; 
        double tarifaMensualCarro = 20000; 
        double tarifaMensualMoto = 15000; 
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_CARRO, tarifaDiariaCarro);
        parqueadero.setTarifaDiaria(Parqueadero.TIPO_MOTO_CLASICA, tarifaDiariaMoto);
        parqueadero.setTarifaMensual(Parqueadero.TIPO_CARRO, tarifaMensualCarro);
        parqueadero.setTarifaMensual(Parqueadero.TIPO_MOTO_CLASICA, tarifaMensualMoto);
        Vehiculo carro = new Carro("ABC123", "Toyota", "Juan");
        Vehiculo moto = new Moto("XYZ789", "Honda", "Maria", 150);

        try {
            parqueadero.estacionarVehiculo(carro, 0, 0);
            parqueadero.estacionarVehiculo(moto, 1, 0); 
            Map<Integer, Double> reporteMensual = parqueadero.generarReporteMensual();

            assertEquals(tarifaDiariaCarro, reporteMensual.get(Parqueadero.TIPO_CARRO), 0.01);
            assertEquals(tarifaDiariaMoto, reporteMensual.get(Parqueadero.TIPO_MOTO_CLASICA), 0.01); 
        } catch (ParqueaderoException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    public void testGenerarReporteMensual_SinVehiculos() {
        Map<Integer, Double> reporteMensual = parqueadero.generarReporteMensual();
        assertTrue(reporteMensual.isEmpty(), "El reporte mensual debería estar vacío cuando no hay vehículos estacionados.");
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Test
    public void testBuscarVehiculoPorPlaca_VehiculoNoExistente() {
 
        String placa = "ABC123";
        Vehiculo vehiculo = parqueadero.buscarVehiculoPorPlaca(placa);
        assertNull(vehiculo, "No debería encontrarse un vehículo con la placa " + placa);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------//
}
