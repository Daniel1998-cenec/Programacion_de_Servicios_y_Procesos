package ultimoApunte;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class EjercicioFecha {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la nueva fecha (en formato MM-dd-yyyy):");
        String nuevaFechaStr = scanner.nextLine();

        if (validarFecha(nuevaFechaStr)) {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "date " + nuevaFechaStr);

            Process p = pb.start();
            InputStream is = p.getInputStream();
            OutputStream os = p.getOutputStream();

            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }

            System.out.println("Presiona Enter para finalizar:");
            scanner.nextLine();

            is.close();

            // Espera a que el proceso termine
            try {
                int exitCode = p.waitFor();
                System.out.println("Proceso terminado con código de salida: " + exitCode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fecha no válida. Debe estar en el formato MM-dd-yyyy.");
        }
    }

    public static boolean validarFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}