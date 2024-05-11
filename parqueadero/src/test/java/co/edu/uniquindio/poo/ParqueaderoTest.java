/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;



/**
 * Unit test for simple App.
 */
public class ParqueaderoTest {
    private Parqueadero parqueadero;

    @BeforeEach
    void setUp() {
        parqueadero = new Parqueadero(5, 5);
    }

    @Test
    void puestoDisponible() {
        assertTrue(parqueadero.puestoDisponible(0, 0));
        parqueadero.estacionarVehiculo(new Carro("765XYz", "Mercedes", "Rojas"), 0, 0);
        assertFalse(parqueadero.puestoDisponible(0, 0));
    }

    @Test
    void estacionatYDesocuparVehiculo() {
        Vehiculo moto = new Moto("827JSLk", "Yamaha", "Esteban", 127);
        assertTrue(parqueadero.estacionarVehiculo(moto, 1, 1));
        assertEquals(moto, parqueadero.desocuparPuesto(1, 1));
    }

    @Test
    void identificarPropietario() {
        Vehiculo Carro = new Carro("387KJH", "Toyota", "José");
        parqueadero.estacionarVehiculo(Carro, 2, 2);
        assertEquals("José", parqueadero.identificarPropietario(2, 2));
    }

    @Test
    void registroIngresos() {
        Vehiculo moto = new Moto("928KHS", "Honda", "Miguel", 127);
        parqueadero.estacionarVehiculo(moto, 3, 3);
        Map<String, Double> tarifas = new HashMap<>();
        tarifas.put("moto", 2.0);
        parqueadero.setTarifas(tarifas);

        parqueadero.registrarIngreso(moto, 3, 3);
        assertEquals(0.0, parqueadero.getRegistroIngresos().get(moto.getPlaca()));
    }

    @Test
    void calcularCosto() {
        Vehiculo moto = new Moto("093JHB", "Kawasaki", "Camilo", 230);
        parqueadero.estacionarVehiculo(moto, 4, 4);
        Map<String, Double>tarifas = new HashMap<>();
        tarifas.put("moto", 2.0);
        parqueadero.setTarifas(tarifas);

        parqueadero.registrarIngreso(moto, 4, 4);
        assertEquals(6.0, parqueadero.calcularCosto(moto.getPlaca(), 3));
    }

    @Test
    void resgistroIngresosMultiplesVehiculos(){
        Vehiculo carro1 = new Carro("737IJH", "toyota", "Stiven");
        Vehiculo moto1 = new Moto("938KHH", "Susuki", "Daniel", 189);

        parqueadero.estacionarVehiculo(carro1, 2, 2);
        parqueadero.estacionarVehiculo(moto1, 3, 3);

        Map<Integer, Double> tarifas = new HashMap<>();
        tarifas.put(Parqueadero.TIPO_CARRO, 5.0);
        tarifas.put(Parqueadero.TIPO_MOTO_HIBRIDA, 2.0);
        parqueadero.setTarifas(tarifas);

        parqueadero.registrarIngreso(carro1, 2, 2);
        parqueadero.registrarIngreso(moto1, 3, 3);

        assertEquals(5.0, parqueadero.getRegistroEntradas().get(carro1.getPlaca()));
        assertEquals(2.0, parqueadero.getRegistroIngresos().get(moto1.getPlaca()));
    }

    @Test
    void clacularCostoMultiplesTarifas() {
        Vehiculo carro = new Carro("737IJH", "toyota", "Stiven");
        Vehiculo moto = new Moto("938KHH", "Susuki", "Daniel", 189);

        parqueadero.estacionarVehiculo(carro, 2, 2);
        parqueadero.estacionarVehiculo(moto, 3, 3);

        Map<String, Double> tarifas = new HashMap<>();
        tarifas.put(Parqueadero.TIPO_CARRO, 5.0);
        tarifas.put(Parqueadero.TIPO_MOTO_HIBRIDA, 2.0);
        parqueadero.setTarifas(tarifas);

        parqueadero.registrarIngreso(carro, 2, 2);
        parqueadero.registrarIngreso(moto, 3, 3);

        // Suponiendo que los vehiculos estuvieron por 5 horas
        assertEquals(15.0, parqueadero.calcularCosto(carro.getPlaca(), 5));
        assertEquals(6.0, parqueadero.calcularCosto(moto.getPlaca(), 5));
    }  
}
    
