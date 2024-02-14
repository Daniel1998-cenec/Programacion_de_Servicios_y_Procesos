package Actividad1streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 6000;

		System.out.println("PROGRAMA CLIENTE INICIADO...");

		try {
			Socket cliente = new Socket(host, puerto);

			OutputStream salida = cliente.getOutputStream();
			DataOutputStream flujoSalida = new DataOutputStream(salida);

			double cantidadEuros = 100;
			flujoSalida.writeUTF(Double.toString(cantidadEuros));

			DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

			String respuestaServidor = flujoEntrada.readUTF();
			System.out.println("Recibiendo del SERVIDOR: \n" + respuestaServidor);

			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}