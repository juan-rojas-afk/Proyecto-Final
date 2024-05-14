package co.edu.uniquindio.poo;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa un poruqeadero en donde se puede esacionar un vehiculo
 */

public class Parqueadero {
    private Vehiculo[][] puestos; 
    private boolean[][] puestosOcupados;
    private Map<Integer, Double> tarifas;
    private Map<String, LocalDateTime> registroEntradas;
    private Map<Integer, Double> tarifasPorHora;
    private Map<Integer, Double> tarifasDiarias;
    private Map<Integer, Double> tarifasMensuales;
    private Map<Integer, Double> ingresosMensaules;
    private int filas;
    private int columnas;


    // Constante para tipos de vehiculos
    public static final Integer TIPO_CARRO = 1;
    public static final Integer TIPO_MOTO_CLASICA = 2;
    public static final Integer TIPO_MOTO_HIBRIDA = 3;

    public Parqueadero(int filas, int columnas) {
        puestos = new Vehiculo[filas][columnas];
        tarifas = new HashMap<>();
        registroEntradas = new HashMap<>();
        tarifasPorHora = new HashMap<>();
        tarifasDiarias = new HashMap<>();
        tarifasMensuales = new HashMap<>();
        ingresosMensaules = new HashMap<>();
        puestosOcupados = new boolean[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Vehiculo[][] getPuestos() {
        return puestos;
    }

    public Map<Integer, Double> getTarifas() {
        return tarifas;
    }

    public void setTarifaPorHora(int tipoVehiculo, double tarifaPorHora) {
        tarifasPorHora.put(tipoVehiculo, tarifaPorHora);
    }

    public void setTarifaDiaria(int tipoVehiculo, double tarifasDiaria) {
        tarifasDiarias.put(tipoVehiculo, tarifasDiaria);
    }

    public void setTarifaMensual(int tipoVehiculo, double tarifasMensual) {
        tarifasMensuales.put(tipoVehiculo, tarifasMensual);
    }

    public boolean puestoDisponible(int fila, int columna) {
        return puestos[fila][columna] == null;
    }

    public Map<Integer, Double> getTarifasPorHora() {
        return tarifasPorHora;
    }

    public Map<Integer, Double> getTarifasDiarias() {
        return tarifasDiarias;
    }

    public Map<Integer, Double> getTarifasMensuales() {
        return tarifasMensuales;
    }
    
    //Estaciona el vehiculo en un lugar en especifico
    public boolean estacionarVehiculo (Vehiculo vehiculo, int fila, int columna) throws ParqueaderoException {
        if (fila < 0 || fila >= filas || columna < 0 || columnas >= columnas) {
            throw new FueraDeLimitesException("La posición especifica está fuera de los límites del parqueadero.");
        }
        if (!puestoDisponible(fila, columna)) {
            return false;
        }
        String placa = vehiculo.getPlaca();
        if (!validarFormatoPlaca(placa)) {
            throw new FormatoPlacaException("La placa del vehículo debe tener el formato ABC123"); 
        }

        String propietario = vehiculo.getPropietario();
        if (!validarNombrePropietario(propietario)) {
            throw new NombrePropietarioException("El nombre del propietario del vehículo solo puede contener letras");
        }

        puestos[fila][columna] = vehiculo;
        puestosOcupados[fila][columna] = true;
        registroEntradas.put(vehiculo.getPlaca(), LocalDateTime.now());
        return true;
    }

    private boolean validarFormatoPlaca(String placa) {
        return placa.matches("[A-Z]{3}\\d{3}");
    }

    private boolean validarNombrePropietario(String nombre) {
        return nombre.matches("[A-Za-z]+");
    }

   

    //Desocupa un puesto del estacionamiento y devulve el vehiculo estacionado
    public Vehiculo desocuparPuesto(int fila, int columna) {
        Vehiculo vehiculo = puestos[fila][columna];
        puestos [fila][columna] = null;
        return vehiculo;
    }

    //Registra la salida dl vehiculo y calcula el costo por el tiempo estacionado
    public double registrarSalida(String placa) {
        LocalDateTime horaEntrada = registroEntradas.get(placa);
        LocalDateTime horaSalida = LocalDateTime.now();

        for (Vehiculo[] fila : puestos) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null && vehiculo.getPlaca().equals(placa)) {
                    int  tipoVehiculo = vehiculo.getTipo();
                    double costo = calcularCostoEstadia(horaEntrada, horaSalida, tipoVehiculo);
                    return costo;
                }
            }
        }
        System.out.println("Error: No se encontró el vehículo con la placa especificada.");
        return 0.0;
    }

    //Obtiene el nombre del propietario en base a la ubicación del vehiculo
    public String identificarPropietario(int fila, int columna) {
        return puestos[fila][columna] != null ? puestos[fila][columna].getPropietario() : null;
    }

    public void registrarIngreso(Vehiculo vehiculo, int filas, int columnas) {
        registroEntradas.put(vehiculo.getPlaca(), LocalDateTime.now());
    }

    public Map<String, LocalDateTime> getRegistroEntradas() {
        return registroEntradas;
    }

    private double calcularCostoEstadia(LocalDateTime horaEntrada, LocalDateTime horaSalida, int tipoVehiculo) {
        Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
        long horas = tiempoTranscurrido.toHours();
        double tarifaHora = tarifas.getOrDefault(tipoVehiculo, 0.0);
        return horas * tarifaHora;
    }

    public void setTarifas(int tipoVehiculo, double tarifa) {
        tarifas.put(tipoVehiculo, tarifa);
    }

    public double calcularcosto(String placa, int horas) {
        LocalDateTime horaEntrada = registroEntradas.get(placa);
        LocalDateTime horaSalida = horaEntrada.plusHours(horas);
        Vehiculo vehiculo = puestos[0][0];
        int tipoVehiculo = vehiculo.getTipo();
        return calcularCostoEstadia(horaEntrada, horaSalida, tipoVehiculo);
    }

    public Map<Integer, Double> generarReporteDiario() {
        Map<Integer, Double> reporteDiario = new HashMap<>();

        for (Map.Entry<String, LocalDateTime> entry : registroEntradas.entrySet()) {
            String placa = entry.getKey();
            LocalDateTime horaEntrada = entry.getValue();
            LocalDateTime horaSalida = LocalDateTime.now();
            Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
            long horas = tiempoTranscurrido.toHours();

            if (puestos != null && puestos.length > 0 && puestos[0].length > 0) {
                for (int i = 0; i < puestos.length; i ++) {
                    for (int j = 0; j < puestos[0].length; j++) {
                        Vehiculo vehiculo = puestos[i][j];
                        if (vehiculo != null && vehiculo.getPlaca().equals(placa)) {
                            double total = reporteDiario.getOrDefault(vehiculo.getTipo(), 0.0);
                            double tarifaPorHora = tarifasPorHora.getOrDefault(vehiculo.getTipo(), 0.0);
                            double tarifaDiaria = tarifasDiarias.getOrDefault(vehiculo.getTipo(), 0.0);
                            total += horas * tarifaPorHora * tarifaDiaria;
                            reporteDiario.put(vehiculo.getTipo(), total);
                        }
                    }
                }
            } else {
                System.out.println("Error: No se pudo generar el reporte Diario. No hay información de puestos disponibles");
                return null;
            }
        } return reporteDiario;
    }

    public Map<Integer, Double> generarReporteMensual() {
        Map<Integer, Double> reporteMensual = new HashMap<>();
    
        for (Map.Entry<String, LocalDateTime> entry : registroEntradas.entrySet()) {
            String placa = entry.getKey();
            LocalDateTime horaEntrada = entry.getValue();
            LocalDateTime horaSalida = LocalDateTime.now();
            Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
            long diasEstadia =  tiempoTranscurrido.toDays();

            if (diasEstadia > 0) {
                // Cobrar tarifa diaria o mensual según corresponda 
                Vehiculo vehiculo = buscarVehiculoPorPlaca(placa);
                if (vehiculo != null) {
                    double total = reporteMensual.getOrDefault(vehiculo.getTipo(), 0.0);
                    if (diasEstadia == 1) {
                        double tarifaDiaria = tarifasDiarias.getOrDefault(vehiculo.getTipo(), 0.0);
                        total += tarifaDiaria;
                    } else {
                        double tarifaMensual = tarifasMensuales.getOrDefault(vehiculo.getTipo(), 0.0);
                        total += tarifaMensual;
                    }
                    reporteMensual.put(vehiculo.getTipo(), total);
                }
            }
        }
        return reporteMensual;
    }

    public double obtenerIngresosMensuales(int mes) {
        return ingresosMensaules.getOrDefault(mes, 0.0);
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo[] fila : puestos) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null && vehiculo.getPlaca().equals(placa)) {
                    return vehiculo;
                }
            }
        }
        return null;
    }

    public void guardarDatos(String nombreArchivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream(nombreArchivo))) {
            outputStream.writeObject(registroEntradas);
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos(String nombreArchivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            registroEntradas = (Map<String, LocalDateTime>) inputStream.readObject();
            System.out.println("Datos cargados correctamente");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("no se pudo cargar los datos: " + e.getMessage());
    }
}

    public void setTarifas(Map<String, Double> tarifas2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarifas'");
    }
}
