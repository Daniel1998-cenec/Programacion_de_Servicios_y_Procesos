package Actividad3streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

			String operacion = "division";
			double num1 = 10.5;
			double num2 = 2.5;
			flujoSalida.writeUTF(operacion);
			flujoSalida.writeDouble(num1);
			flujoSalida.writeDouble(num2);

			System.out.println("Enviando al servidor: " + operacion + ", " + num1 + " y " + num2);

			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);

			double resultado = flujoEntrada.readDouble();
			System.out.println("Recibido del servidor: " + resultado);

			flujoSalida.close();
			flujoEntrada.close();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}