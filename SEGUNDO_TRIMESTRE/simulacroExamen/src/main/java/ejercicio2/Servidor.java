package ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(61000);
            System.out.println("Servidor esperando conexiones...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostName());

            // Flujo de entrada para recibir el objeto Totodile del cliente
            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

            // Flujo de salida para enviar datos al cliente
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Recibir el objeto Totodile del cliente
            Totodile totodile = (Totodile) entradaObjeto.readObject();
            System.out.println("Tu totodile se llama " + totodile.getNombre());

            // Mandar mensaje al cliente
            salida.writeUTF("¿Quieres usar una piedra agua en tu totodile " + totodile.getNombre() + "? (Y/N)");

            // Flujo de entrada para recibir respuesta del cliente
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            String respuestaCliente = entrada.readUTF();

            // Procesar la respuesta del cliente
            if (respuestaCliente.equalsIgnoreCase("Y")) {
                // Cambiar el nombre del Totodile a "Croconaw "
                totodile.setNombre("Croconaw");
                // Enviar el objeto Totodile evolucionado y un mensaje al cliente
                ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
                salidaObjeto.writeObject(totodile);
                salidaObjeto.flush();
                // Cerrar flujo de salida de objetos
                salidaObjeto.close();
            } else {
                // Enviar un mensaje al cliente
                salida.writeUTF("Tu comapñero y amigo "+ totodile.getNombre()+" se que queda igual que estaba");
            }

            // Cerrar flujos y socket
            salida.close();
            entrada.close();
            entradaObjeto.close();
            cliente.close();
            servidor.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
