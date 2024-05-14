package co.edu.uniquindio.poo;

import java.util.Map;
import java.util.Scanner;

//Clase principal para la aplicación
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static Parqueadero parqueadero;

    /** Método inicial que inicia la aplicación
     * 
     */
    public static void main(String[] args) throws ParqueaderoException{
        System.out.println("Bienvenido al menú del parqueadero");

        parqueadero = null; //inicialmente el parqueadero no está configurado 

        boolean salir = false;
        while (!salir) {
            System.out.println("\nSeleccione una opción");
            System.out.println("1. Configurar valores de tarifas");
            System.out.println("2. Configurar Tamaño del parqueadero");
            System.out.println("3. Estacionar vehiculo");
            System.out.println("4. Desocupar puesto");
            System.out.println("5. Identiifcar propietario del vehiculo");
            System.out.println("6. Generar reporte diario");
            System.out.println("7. Generar reporte mensual");
            System.out.println("8. Estado del Parqueadero ");
            System.out.println("9. Salir");

            int opcion = obtenerEnteroValido("Ingrese el número de opción: ", 1, 8);


            try {
                switch (opcion) {
                    case 1:
                        System.out.println("Configuración de tarifas");
                        if (parqueadero == null) {
                            System.out.println("Primero configure el tamaño del parqueadero antes de establecer las tarifas.");
                            break;
                        }

                        // Tarifas por Hora 
                        System.out.println("Ingresenlas tarifas por hora para cada tipo de vehículo: ");
                        double tarifaHoraCarro = obtenerDoubleValido("Tarifa por hora para carro: ");
                        double tarifaHoraMotoClasica = obtenerDoubleValido("Tarifa por hora para moto clásica");
                        double tarifaHoraMotoHibrida = obtenerDoubleValido("Tarifa por hora para moto clásica");
                        parqueadero.setTarifaPorHora(Parqueadero.TIPO_CARRO, tarifaHoraCarro);
                        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_CLASICA,tarifaHoraMotoClasica);
                        parqueadero.setTarifaPorHora(Parqueadero.TIPO_MOTO_HIBRIDA, tarifaHoraMotoHibrida);

                        // Tarifas por día 
                        System.out.println("Ingrese las tarifas diarias y mensuales para cada tipo de vehículo: ");
                        System.out.println("1. Carro");
                        double tarifaDiariaCarro = obtenerDoubleValido("Tarifa diaria para caroo: ");
                        double tarifaMensualCarro = obtenerDoubleValido("Tarifa mensual para carro: ");
                        parqueadero.setTarifaDiaria(Parqueadero.TIPO_CARRO, tarifaDiariaCarro);
                        parqueadero.setTarifaMensual(Parqueadero.TIPO_CARRO, tarifaMensualCarro);

                        System.out.println("2. Moto Clásica");
                        double tarifaDiariaMotoClasica = obtenerDoubleValido("Tarifa diaria para moto clásica: ");
                        double tariafaMensualMotoClasica = obtenerDoubleValido("Tarifa mensual para moto clásica: ");
                        parqueadero.setTarifaDiaria(Parqueadero.TIPO_MOTO_CLASICA, tarifaDiariaMotoClasica);
                        parqueadero.setTarifaMensual(Parqueadero.TIPO_MOTO_CLASICA, tariafaMensualMotoClasica);

                        System.out.println("3. Moto Híbrida");
                        double tarifaDiariaMotoHibrida = obtenerDoubleValido("Tarifa diaria para moto híbrida: ");
                        double tarifaMensualMotoHibrida = obtenerDoubleValido("Tarifa menusla para moto híbrida: ");
                        parqueadero.setTarifaDiaria(Parqueadero.TIPO_MOTO_HIBRIDA, tarifaDiariaMotoHibrida);
                        parqueadero.setTarifaMensual(Parqueadero.TIPO_MOTO_HIBRIDA, tarifaMensualMotoHibrida);
                        break;
                    
                    case 2:
                        System.out.println("Configuración del tamaño del parqueadero");
                        System.out.println("Ingrese el número de filas del parqueadero (entre 1 y 100). ");
                        int filas = obtenerEnteroValido("", 1, 100);
                        System.out.println("Ingrese el número de columnas del parqueadero (entre 1 y 100): ");
                        int columnas = obtenerEnteroValido("", 1, 100);
                        parqueadero = new Parqueadero(filas, columnas);
                        break;

                    case 3:
                        System.out.println ("Estacionar vehículo");
                        System.out.println("Ingrese la placa del vehiculo formato: ");
                        String placa = scanner.nextLine();
                        System.out.println("Ingrese el modelo del vehiculo: ");
                        String modelo = scanner.nextLine();
                        System.out.println("Ingrese el nombre del propietario del vehiculo: ");
                        String propietario = scanner.nextLine();
                        System.out.println("Ingrese el modelo del vehiculo (1: Carro, 2: Moto Clasica, 3: Moto hibrida): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        Vehiculo vehiculo;
                        if (tipo == 1) {
                            vehiculo = new Carro(placa, modelo, propietario);   
                        } else if (tipo == 2 || tipo == 3) { 
                            System.out.println("Ingrese la velocidad maxima del vehicuo: ");
                            int velocidadMaxima = scanner.nextInt();
                            scanner.nextLine();
                            vehiculo = new Moto(placa, modelo, propietario, velocidadMaxima);
                        } else {
                            System.out.println("Tipo de vehiculo no valido");
                            continue;
                        }

                        System.out.println("Ingrese la fila y columna del puesto (fila columna): ");
                            int fila = obtenerEnteroValido("Fila: ", 0, parqueadero.getFilas() - 1);
                            int columna = obtenerEnteroValido("Columna: ", 0, parqueadero.getColumnas() - 1);

                            try {
                                if (parqueadero.estacionarVehiculo(vehiculo, fila, columna)) {
                                    System.out.println("Vehículo estacionado correctamente");
                                } else {
                                    System.out.println("No se pudo estacionar el vehículo. El puesto se encuentra ocupado");
                                }
                            }catch (FueraDeLimitesException e) {
                                System.out.println ("Error: " + e.getMessage());
                            } catch (FormatoPlacaException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (NombrePropietarioException e) {
                                System.out.println("Error: " + e.getMessage());
                            }

                        break;
                    case 4:
                        System.out.println("Ingrese la fila del uesto a desocupar: ");
                        fila = scanner.nextInt();
                        System.out.println("Ingrese la columna del puesto a desocupar: ");
                        columna = scanner.nextInt();

                        Vehiculo vehiculoDesocupado = parqueadero.desocuparPuesto(fila, columna);
                        if (vehiculoDesocupado != null) {
                            System.out.println("Se ha desocupado el pesuto de manera correcta");
                        } else {
                            System.out.println("El puesto se encuentra vacio");
                        }
                        break;

                    case 5:
                        System.out.println("Ingrese la fila del puesto para identificar al propietario: ");
                        fila = scanner.nextInt();
                        System.out.println("Ingrese la columna del puesto para identificar al propietario:  ");
                        columna = scanner.nextInt();

                        String propietarioIdentificado = parqueadero.identificarPropietario(fila, columna);
                        if (propietarioIdentificado != null) {
                            System.out.println("El propietario del vehidulo en ese puesto es: " + propietarioIdentificado);
                        } else {
                            System.out.println("El puesto se encuentra vacio");
                        }
                        break;

                    case 6: 
                        Map<Integer, Double>  reporteDiario = parqueadero.generarReporteDiario();
                        System.out.println("Reporte diario: ");
                        for (Map.Entry<Integer, Double> entry : reporteDiario.entrySet()) {
                            int tipoVehiculo = entry.getKey();
                            double costo = entry.getValue();
                            System.out.println("Tipo de vehículo: " + tipoVehiculo + ", Costo: $" + costo);
                        }
                        return;

                    case 7:
                        Map<Integer, Double> reporteMensual = parqueadero.generarReporteMensual();
                        System.out.println("Reporte mensual: ");
                        for (Map.Entry<Integer, Double> entry : reporteMensual.entrySet()) {
                            int tipoVehiculo = entry.getKey();
                            double costoPorHora = parqueadero.getTarifas().getOrDefault(tipoVehiculo, 0.0);
                            double costoPorDia = parqueadero.getTarifasDiarias().getOrDefault(tipoVehiculo, 0.0);
                            double costoMensual = parqueadero.getTarifasMensuales().getOrDefault(tipoVehiculo, 0.0);

                            double total = entry.getValue();

                            total += costoPorHora + costoPorDia + costoMensual;
                            System.out.println("tipo de vehículo: " + tipoVehiculo + ", Costo total: $" + total);
                        }
                        break;
                    
                    case 8:
                    System.out.println("Estado del parqueadero");
                    for (int i = 0; i < parqueadero.getFilas(); i++) {
                        for (int j = 0; j < parqueadero.getColumnas(); j++) {
                            if (parqueadero.puestoDisponible(i, j)) {
                                System.out.print("[0] ");
                            } else {
                                System.out.print("[X] ");
                            }
                        }
                        System.out.println();
                    }
                    break;

                    case 9:
                        salir = true;
                        System.out.println("Saliendo del Sustema del parqeuadero. ¡Feliz día!");
                        break;
                    default:
                        System.out.println("Opción invalida. Por favor, seleccione una opción valida");
                    }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                ErrorLogger.logError(e);
            }     
        }
    }

    public static int obtenerEnteroValido(String mensaje, int min, int max) {
        int valor;
        while (true) {
            System.out.println(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor < min || valor > max ) {
                    System.out.println("error: Ingrese un número entre " + min + " y " + max + ".");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error. Ingrese un número válido");
            }
        }
        return valor;
    }

    private static double obtenerDoubleValido(String mensaje) {
        double valor; 
        while (true) {
            System.out.println(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error. Ingrese un número válido");
            }  
        }
        return valor;
    }
}

