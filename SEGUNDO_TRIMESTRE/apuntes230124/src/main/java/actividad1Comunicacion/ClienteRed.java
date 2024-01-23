package actividad1Comunicacion;

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

            // Solicita al usuario ingresar un mensaje
            System.out.print("Ingrese un mensaje: ");
            String mensaje = scanner.nextLine();

            // Envía el mensaje al servidor
            oos.writeObject(mensaje);
            oos.flush(); // Asegura que los datos se envíen correctamente
            System.out.println("Cliente: Enviado mensaje al servidor: " + mensaje);

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            String mensajeEnMinusculas = ois.readObject().toString();
            System.out.println("Cliente: Recibido mensaje en minúsculas del servidor: " + mensajeEnMinusculas);

        } catch (IOException | ClassNotFoundException e) {
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
