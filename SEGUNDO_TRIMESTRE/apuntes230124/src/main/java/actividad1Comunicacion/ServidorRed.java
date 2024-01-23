package actividad1Comunicacion;

import java.io.IOException;
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

            // Recibe el mensaje del cliente
            String mensaje = ois.readObject().toString();
            System.out.println("Servidor: Recibido mensaje: " + mensaje);

            // Convierte a minúsculas
            String mensajeEnMinusculas = mensaje.toLowerCase();

            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(mensajeEnMinusculas);
            oos.flush(); // Asegura que los datos se envíen correctamente
            System.out.println("Servidor: Enviado mensaje en minúsculas al cliente: " + mensajeEnMinusculas);

        } catch (IOException | ClassNotFoundException e) {
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