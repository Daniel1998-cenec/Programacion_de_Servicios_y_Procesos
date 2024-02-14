package Actividad1streams;

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

				String mensaje = flujoEntrada.readUTF();
				System.out.println("Recibido del cliente: " + mensaje);

				double euros = Double.parseDouble(mensaje);
				double dolares = euros * 1.14;
				double libras = euros * 0.87;

				OutputStream salida = cliente.getOutputStream();
				DataOutputStream flujoSalida = new DataOutputStream(salida);

				flujoSalida.writeUTF("Cantidad en euros: " + euros + "\n" + "Cantidad en dólares: " + dolares + "\n"
						+ "Cantidad en libras: " + libras);

				flujoEntrada.close();
				flujoSalida.close();
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}