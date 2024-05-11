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
            System.out.println("7. Estado del Parqeuadero ");
            System.out.println("8. Salir");

            int opcion = obtenerEnteroValido("Ingrese el número de opción: ", 1, 8);


            try {
                switch (opcion) {
                    case 1:
                        System.out.println("Configuración de tarifas por hora");
                        if (parqueadero == null) {
                            System.out.println("Primero configure el tamaño del parqueadero antes de establecer las tarifas.");
                            break;
                        }
                        System.out.println("Ingrese el precio por hora para carros: ");
                        double precioCarros = scanner.nextDouble();
                        parqueadero.setTarifas(Parqueadero.TIPO_CARRO, precioCarros);
                        System.out.println("Ingrese el valor por hora para motos clásicas");
                        double precioMotosClasicas = scanner.nextDouble();
                        parqueadero.setTarifas(Parqueadero.TIPO_MOTO_CLASICA, precioMotosClasicas);
                        System.out.println("Ingrese el valor por hora para motos híbridas");
                        double precioMotosHibridas = scanner.nextDouble();
                        parqueadero.setTarifas(Parqueadero.TIPO_MOTO_HIBRIDA, precioMotosHibridas);
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
                        Map<Integer, Double> reporteDiario = parqueadero.generarReporteDiario();
                        System.out.println("Reporte diario: ");
                        for (Map.Entry<Integer, Double> entry : reporteDiario.entrySet()) {
                            String tipoVehiculo = "";
                            switch (entry.getKey()) {
                                case 1:
                                    tipoVehiculo = "Carro";
                                    break;
                                case 2:
                                    tipoVehiculo = "Moto clasica";
                                    break;
                                case 3:
                                    tipoVehiculo = "Moto hibrida";
                                    break;
                            }
                            System.out.println("- Tipo. " + tipoVehiculo + ", Total recaudado: $" + entry.getValue());
                        }
                        break;
                    
                    case 7:
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

                    case 8:
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
}

