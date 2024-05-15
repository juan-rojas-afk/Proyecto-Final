package co.edu.uniquindio.poo;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase Parqueadero representa un estacionamiento deonde se pueden estacionar vehículos.
 * Permite gestionar el ingreso, salida y estado de los vehículos, así como configurar tarifas.
 * y generar reportes de ingresos.
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

    /**
     * Constructor de la clase Parqueadero
     * 
     * @param filas El número de filas en el parqueadero.
     * @param columnas El número de columnas en el parqueadero.
     */
    public Parqueadero(int filas, int columnas) {
        // Inicialización de los atributos y mapas.
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

    /**
     * Devuelve el número de filas en el parqueadero.
     * 
     * @return
     */

    public int getFilas() {
        return filas;
    }

    /**
     * Devuelve el número de columnas del parqueadero.
     * 
     * @return
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Devuelve la matriz que representa los puestos de estacionamiento del parqueadero.
     * 
     * @return Una matriz de vehículos representando los puestos del parqueadero
     */
    public Vehiculo[][] getPuestos() {
        return puestos;
    }

    /**
     * Devuelve un mapa que contiene las tarifas asociadas a cada tipo de vehículo.
     * 
     * @return Un mapa de tipo de vehículo a tarifa.
     */
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

    /**
     * Devuelve  un mapa que contiene las tarifas por hora asociadas a cada tipo de vehículo.
     * 
     * @return Un mapa de tipo de vehículos a tarifa por hora.
     */
    public Map<Integer, Double> getTarifasPorHora() {
        return tarifasPorHora;
    }

    /**
     * Devuelve un mapa que contiene las tarifas diarias asociadas a cada tipo de vehículos.
     * 
     * @return Un mapa de tipo de vehículo a tarifa diaria.
     */
    public Map<Integer, Double> getTarifasDiarias() {
        return tarifasDiarias;
    }

    /**
     * Devuelve un mapa que contiene las tarifas mensuales asociadas a cada tipo de vehículo.
     * 
     * @return Un mapa de tipo vehículo a tarifa mensual.
     */
    public Map<Integer, Double> getTarifasMensuales() {
        return tarifasMensuales;
    }
    
    /**
     * Estaciona un vehículo en un liar específico del parqueadero.
     * 
     * @param vehiculo El vehículo a estacionar.
     * @param fila La fila donde se desea estacionar el vehículo.
     * @param columna La columna donde se desea estacionar el vehículo.
     * @return true si el vehículo se estaciona correctamente, false si el puesto está ocupado.
     * @throws ParqueaderoException Si ocurre un error duarante el proceso de estacionamiento.
     */
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


    /**
     * Desocupa un puesto de estacionamiento en el parqueadero.
     * 
     * @param fila La fila del puesto a desocupar.
     * @param columna La columna del puesto a desocupar.
     * @return El vehículo que estaba estacionado en el puesto, o null si el puesto estaba vacío.
     */
    public Vehiculo desocuparPuesto(int fila, int columna) {
        Vehiculo vehiculo = puestos[fila][columna];
        puestos [fila][columna] = null;
        return vehiculo;
    }

    /**
     * Registra la salida de un vehículo del parqueadero y calcula el costo de la estadía.
     * 
     * @param placa La placa del vehículo que sale del parqueadero.
     * @return El costo de la estadía del vehículo.
     */
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

    /**
     * Identifica al propietario de un vehículo estacionado en un puesto del parqueadero.
     * 
     * @param fila La fila del puesto de estacionamiento.
     * @param columna La columna del puesto de estacionamiento.
     * @return El nombre del propietario del vehículo estacionado en el puesto, o null si está vacío.
     */
    public String identificarPropietario(int fila, int columna) {
        return puestos[fila][columna] != null ? puestos[fila][columna].getPropietario() : null;
    }

    /**
     * Registra el ingreso de un vehículo al parqueadero.
     * 
     * @param vehiculo El vehículo que ingresa al parqueadero.
     * @param fila La fila del puesto donde se estaciona el vehículo.
     * @param columna La columna del puesto donde se estaciona el vehículo.
     */
    public void registrarIngreso(Vehiculo vehiculo, int filas, int columnas) {
        registroEntradas.put(vehiculo.getPlaca(), LocalDateTime.now());
    }

    public Map<String, LocalDateTime> getRegistroEntradas() {
        return registroEntradas;
    }

    /**
    * Calcula el costo de la estadía de un vehículo en el parqueadero, basado en el tiempo transcurrido y la tarifa por hora del tipo de vehículo.
    * 
    * @param horaEntrada La hora de entrada del vehículo al parqueadero.
    * @param horaSalida La hora de salida del vehículo del parqueadero.
    * @param tipoVehiculo El tipo de vehículo estacionado.
    * @return El costo de la estadía del vehículo.
    */
    private double calcularCostoEstadia(LocalDateTime horaEntrada, LocalDateTime horaSalida, int tipoVehiculo) {
        // CAlcula la duración de la estadía del vehículo
        Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
        long horas = tiempoTranscurrido.toHours();

        // Obtiene la tarifa por hora del tipo de vehículo
        double tarifaHora = tarifas.getOrDefault(tipoVehiculo, 0.0);

        // Calcula el costo ttal de la estadía 
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

    /**
    * Genera un reporte del costo total de la estadía de cada tipo de vehículo en el parqueadero en un día específico.
    * El reporte se calcula sumando el costo de la estadía de cada vehículo estacionado, tomando en cuenta las tarifas
    * por hora y por día de cada tipo de vehículo.
    * 
    * @return Un mapa que contiene el tipo de vehículo como clave y el costo total de la estadía como valor.
    */
    public Map<Integer, Double> generarReporteDiario() {
        Map<Integer, Double> reporteDiario = new HashMap<>();

        // Itera sobre cada vehículo estacionado para calcular su costo de estadía.
        for (Map.Entry<String, LocalDateTime> entry : registroEntradas.entrySet()) {
            String placa = entry.getKey();
            LocalDateTime horaEntrada = entry.getValue();
            LocalDateTime horaSalida = LocalDateTime.now();
            Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
            long horas = tiempoTranscurrido.toHours();

            // Si hay vehículos estacionados en el parqueadero.
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
                // Si no hay información de puestos disponibles, muestra un mensaje de errr¿or
                System.out.println("Error: No se pudo generar el reporte Diario. No hay información de puestos disponibles");
                return null;
            }
        } return reporteDiario;
    }

    /**
    * Genera un reporte del costo total de la estadía de cada tipo de vehículo en el parqueadero en un mes específico.
    * El reporte se calcula sumando el costo de la estadía de cada vehículo estacionado, tomando en cuenta las tarifas
    * por día y mensuales de cada tipo de vehículo.
    * 
    * @return Un mapa que contiene el tipo de vehículo como clave y el costo total de la estadía como valor.
    */
    public Map<Integer, Double> generarReporteMensual() {
        Map<Integer, Double> reporteMensual = new HashMap<>();

        // Itera sobre cada vehículo estacionado para calcular su costo de estadía mensual
        for (Map.Entry<String, LocalDateTime> entry : registroEntradas.entrySet()) {
            String placa = entry.getKey();
            LocalDateTime horaEntrada = entry.getValue();
            LocalDateTime horaSalida = LocalDateTime.now();
            Duration tiempoTranscurrido = Duration.between(horaEntrada, horaSalida);
            long diasEstadia =  tiempoTranscurrido.toDays();

            // s+Si la estadía es mayor a 0 días
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

     /**
     * Guarda los datos del parqueadero en un archivo.
     * 
     * @param nombreArchivo El nombre del archivo donde se guardarán los datos.
     */
    public void guardarDatos(String nombreArchivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream(nombreArchivo))) {
            outputStream.writeObject(registroEntradas);
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    /**
     * Carga los datos del parqueadero desde un archivo.
     * 
     * @param nombreArchivo El nombre del archivo desde donde se cargarán los datos.
     */
    @SuppressWarnings("unchecked")
    public void cargarDatos(String nombreArchivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            registroEntradas = (Map<String, LocalDateTime>) inputStream.readObject();
            System.out.println("Datos cargados correctamente");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("no se pudo cargar los datos: " + e.getMessage());
    }
}

}
