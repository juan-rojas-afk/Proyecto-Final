package co.edu.uniquindio.poo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorLogger {
    private static final String LOG_FILE = "error-log.txt";

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
