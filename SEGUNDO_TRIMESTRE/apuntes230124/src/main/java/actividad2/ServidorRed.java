package actividad2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorRed {

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket cliente = null;
        int puerto = 62000;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando cliente en el puerto: " + puerto);
            cliente = servidor.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            // Recibe el número entero del cliente
            int numero = ois.readInt();
            System.out.println("Servidor: Recibido número " + numero);

            // Calcula el cuadrado del número
            int cuadrado = numero * numero;

            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeInt(cuadrado);
            oos.flush();  // Asegura que los datos se envíen correctamente
            System.out.println("Servidor: Enviado cuadrado al cliente: " + cuadrado);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                servidor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}