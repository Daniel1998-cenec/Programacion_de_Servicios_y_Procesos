package ejercicio2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import java.io.*;
import java.net.*;

public class Cliente {
	
    public static void main(String[] args) {
        
    	try {
            Socket socket = new Socket("localhost", 61000);

            
            Haunter haunter = new Haunter();

            
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(socket.getOutputStream());
            salidaObjeto.writeObject(haunter);
            salidaObjeto.flush();

            
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String mensajeServidor = entrada.readUTF();
            System.out.println("Servidor: " + mensajeServidor);

            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String respuesta = br.readLine();

            
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF(respuesta);

            // Si el cliente contesta 'Y', recibir el objeto totodile evolucionado del servidor
            if (respuesta.equalsIgnoreCase("Y")) {
                ObjectInputStream entradaObjeto = new ObjectInputStream(socket.getInputStream());
                Haunter haunterEvolucionado = (Haunter) entradaObjeto.readObject();
                System.out.println("¡Felicidades, tu "+haunter.getNombre()+" ha evolucionado a " + haunterEvolucionado.getNombre()+"!");
                
                System.out.println("Las estadisticas actuales de tu " + haunterEvolucionado.getNombre() + " son :\n"+
                "Vida: "+ haunterEvolucionado.getVida()+"\n"+
                "Ataque: "+ haunterEvolucionado.getAtaque()+"\n"+
                "Defensa: "+ haunterEvolucionado.getDefensa());
                
                // Cerrar flujo de entrada de objeto
                entradaObjeto.close();
            } else {
                // Leer el mensaje del servidor
                mensajeServidor = entrada.readUTF();
                System.out.println("Servidor: " + mensajeServidor);
            }

            // Cerrar flujos y socket
            salida.close();
            entrada.close();
            salidaObjeto.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}