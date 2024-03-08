package ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

            
            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

            
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            
            Haunter haunter = (Haunter) entradaObjeto.readObject();
            System.out.println("Tu pokemon elegido es un " + haunter.getNombre());

            
            salida.writeUTF("¿Quieres que tu "+ haunter.getNombre() +" evolucione a Gengar? (Y/N)");

            
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            String respuestaCliente = entrada.readUTF();

           
            if (respuestaCliente.equalsIgnoreCase("Y")) {
               
                haunter.setNombre("Gengar");
                haunter.setAtaque(40);
                haunter.setDefensa(60);
                haunter.setVida(160);
                
                ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
                salidaObjeto.writeObject(haunter);
                salidaObjeto.flush();
                
                salidaObjeto.close();
            } else {
                // Enviar un mensaje al cliente
                salida.writeUTF("Qué lástima");
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