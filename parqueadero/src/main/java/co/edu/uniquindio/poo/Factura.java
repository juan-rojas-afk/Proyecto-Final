package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double costoTotal;

    public Factura(Vehiculo vehiculo, LocalDateTime horaEntrada, LocalDateTime horaSalida, double costoTotal) {
        this.vehiculo = vehiculo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.costoTotal = costoTotal;
    }

    public void imprimirFactura() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Factura");
        System.out.println("Vehículo: " + vehiculo.getTipo());
        System.out.println("PLaca: " + vehiculo.getPlaca());
        System.out.println("Prpietario: " + vehiculo.getPropietario());
        System.out.println("Hora de entrada: " + horaEntrada.format(formatter));
        System.out.println("Hora de Salida: " + horaSalida.format(formatter));
        System.out.println("Costo total : $" + costoTotal); 
    }
    
}
