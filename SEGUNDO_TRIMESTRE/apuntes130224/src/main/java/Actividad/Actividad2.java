package Actividad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Actividad2 {
    public static void main(String[] args) {
        // Contraseña predefinida
        String contraseñaPredefinida = "admin";

        // Encriptar la contraseña predefinida a hexadecimal con SHA-256
        String hashContraseña = encriptarSHA256(contraseñaPredefinida);

        // Scanner para leer la contraseña del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce tu contraseña: ");
        String contraseñaIntroducida = scanner.nextLine();

        // Encriptar la contraseña introducida a hexadecimal con SHA-256
        String hashContraseñaIntroducida = encriptarSHA256(contraseñaIntroducida);

        // Verificar si los hashes coinciden
        if (hashContraseña.equals(hashContraseñaIntroducida)) {
            System.out.println("Contraseña correcta");
        } else {
            System.out.println("Nunca acertarás la admin_password, reinstala Odú. 😈");
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