package Actividad3;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Conexión al servidor en localhost y puerto 12345
            Socket socket = new Socket("localhost", 12345);

            // Flujo de salida para enviar datos al servidor
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            // Mensaje a enviar al servidor
            String mensaje = "El cuco está en el nido.";

            // Encriptar el mensaje a hexadecimal con SHA-256
            String hashMensaje = encriptarSHA256(mensaje);

            // Imprimir el mensaje y el hash enviado
            System.out.println("Mensaje enviado: " + mensaje);
            System.out.println("Hash enviado: " + hashMensaje);

            // Enviar el mensaje y su hash al servidor
            out.println(mensaje);
            out.println(hashMensaje);

            // Cerrar la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para encriptar una cadena a SHA-256 y convertirla a hexadecimal
    public static String encriptarSHA256(String cadena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cadena.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}