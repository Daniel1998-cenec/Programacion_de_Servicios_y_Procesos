package apuntes1601;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ActividadServidor{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int puerto=2026;
		ServerSocket servidor= new ServerSocket(puerto);
		System.out.println("Escuchando en el puerto: " + servidor.getLocalPort());
		
		Socket cliente=servidor.accept();
		
		 System.out.println("Cliente " + cliente + " conectado:");
         System.out.println("Puerto local: " + cliente.getLocalPort());
         System.out.println("Puerto remoto: " + cliente.getPort());
         System.out.println("Nombre del host: " + cliente.getInetAddress().getHostName());
         System.out.println("IP del host: " + cliente.getInetAddress().getHostAddress());
		
		//Socket cliente2= servidor.accept();
		
		
		
		servidor.close();
		
	}

}
