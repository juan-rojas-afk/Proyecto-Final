package co.edu.uniquindio.poo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Esta clase se utiliza para registrar errores en un archivo de registro.
 */
public class ErrorLogger {
    private static final String LOG_FILE = "error-log.txt";

    /**
     * Método estático para registrar un error en el archivo de registro.
     * @param e La excepción que se va a registrar.
     */
    public static void logError(Exception e) {
        try (PrintWriter writer   = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("Timestamp" + LocalDateTime.now());
            writer.println("Error message: "+ e.getMessage());
            writer.println("Stack trace: ");
            e.printStackTrace(writer);
            writer.println();
        } catch (IOException ioException) {
            System.err.println("Error al escribir en el archivo de registro de errores: " + ioException.getMessage());
        }
    }
}
