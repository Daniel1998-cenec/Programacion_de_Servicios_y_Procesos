package Actividad3streams;

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

				String operacion = flujoEntrada.readUTF();
				double num1 = flujoEntrada.readDouble();
				double num2 = flujoEntrada.readDouble();
				System.out.println("Recibido del cliente: " + operacion + ", " + num1 + " y " + num2);

				double resultado = 0;
				switch (operacion) {
				case "suma":
					resultado = num1 + num2;
					break;
				case "resta":
					resultado = num1 - num2;
					break;
				case "multiplicacion":
					resultado = num1 * num2;
					break;
				case "division":
					if (num2 != 0)
						resultado = num1 / num2;
					else
						resultado = Double.POSITIVE_INFINITY; // Indica división por cero
					break;
				default:
					System.out.println("Operación no válida.");
				}

				OutputStream salida = cliente.getOutputStream();
				DataOutputStream flujoSalida = new DataOutputStream(salida);

				flujoSalida.writeDouble(resultado);
				System.out.println("Enviado al cliente: " + resultado);

				flujoEntrada.close();
				flujoSalida.close();
				cliente.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}