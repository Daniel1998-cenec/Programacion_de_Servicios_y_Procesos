package actividad2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteRed {

    public static void main(String[] args) {
        Socket cliente = null;
        Scanner scanner = new Scanner(System.in);

        try {
            cliente = new Socket("localhost", 62000);
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

            // Solicita al usuario ingresar un número entero
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();

            // Envia el número al servidor
            oos.writeInt(numero);
            oos.flush();  // Asegura que los datos se envíen correctamente
            System.out.println("Cliente: Enviado número al servidor: " + numero);

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            int cuadrado = ois.readInt();
            System.out.println("Cliente: Recibido cuadrado del servidor: " + cuadrado);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
