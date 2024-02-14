package Actividad2streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		int puerto = 6000;
		ServerSocket servidor;

		System.out.println("PROGRAMA SERVIDOR INICIADO...");

		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor esperando conexiones...");

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado desde: " + cliente.getInetAddress() + ":" + cliente.getPort());

				InputStream entrada = cliente.getInputStream();
				DataInputStream flujoEntrada = new DataInputStream(entrada);

				double num1 = flujoEntrada.readDouble();
				double num2 = flujoEntrada.readDouble();
				System.out.println("Recibido del cliente: " + num1 + " y " + num2);

				double suma = num1 + num2;

				OutputStream salida = cliente.getOutputStream();
				DataOutputStream flujoSalida = new DataOutputStream(salida);

				flujoSalida.writeDouble(suma);
				System.out.println("Enviado al cliente: " + suma);

				flujoEntrada.close();
				flujoSalida.close();
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}