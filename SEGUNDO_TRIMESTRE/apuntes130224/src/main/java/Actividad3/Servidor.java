package Actividad3;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Inicializar el servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);

            // Esperar a que un cliente se conecte
            System.out.println("Esperando conexión del cliente...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Flujo de entrada para recibir datos del cliente
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            // Leer el mensaje y su hash enviado por el cliente
            String mensajeRecibido = in.readLine();
            String hashRecibido = in.readLine();

            // Imprimir el mensaje y el hash recibido
            System.out.println("Mensaje recibido: " + mensajeRecibido);
            System.out.println("Hash recibido: " + hashRecibido);

            // Encriptar el mensaje recibido a hexadecimal con SHA-256
            String hashMensajeRecibido = encriptarSHA256(mensajeRecibido);

            // Comparar el hash recibido con el hash calculado
            if (hashRecibido.equals(hashMensajeRecibido)) {
                System.out.println("Mensaje recibido.");
            } else {
                System.out.println("El mensaje ha sido interceptado.");
            }

            // Cerrar la conexión
            serverSocket.close();
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