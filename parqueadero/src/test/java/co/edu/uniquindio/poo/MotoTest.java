package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotoTest {

    private Moto moto;

    @BeforeEach
    public void setUp() {
        moto = new Moto("XYZ789", "Honda", "Maria Lopez", 150);
    }

    @Test
    public void testGetPlaca() {
        assertEquals("XYZ789", moto.getPlaca());
    }

    @Test
    public void testGetModelo() {
        assertEquals("Honda", moto.getModelo());
    }

    @Test
    public void testGetPropietario() {
        assertEquals("Maria Lopez", moto.getPropietario());
    }

    @Test
    public void testGetVelocidadMaxima() {
        assertEquals(150, moto.getVelocidadMaxima());
    }

    @Test
    public void testGetTipo() {
        // Supongamos que Parqueadero.TIPO_MOTO_CLASICA es una constante que tiene el valor 2
        assertEquals(2, moto.getTipo());
    }
}
