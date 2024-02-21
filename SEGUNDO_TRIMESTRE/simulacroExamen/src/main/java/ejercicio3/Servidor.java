package ejercicio3;

import java.io.*;
import java.net.*;
import java.security.*;


public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(6000);
			System.out.println("Servidor esperando conexiones...");

			Socket cliente = servidor.accept();
			System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostName());

			// Contraseña almacenada en el servidor
			String contraseñaAlmacenada = "cenec";
			// Encriptar la contraseña almacenada con SHA-256 y convertirla a hexadecimal
			String contraseñaEncriptada = Hexadecimal(encriptarSHA256(contraseñaAlmacenada));

			// Flujo de salida para enviar datos al cliente
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

			// Flujo de entrada para recibir datos del cliente
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());

			// Mensaje al cliente solicitando contraseña
			salida.writeUTF("Ingrese la contraseña:");

			// Recibir la contraseña del cliente
			String contraseñaCliente = entrada.readUTF();

			// Encriptar la contraseña recibida del cliente con SHA-256 y convertirla a
			// hexadecimal
			String contraseñaClienteEncriptada = Hexadecimal(encriptarSHA256(contraseñaCliente));

			// Comparar las contraseñas
			if (contraseñaEncriptada.equals(contraseñaClienteEncriptada)) {
				
				salida.writeUTF("Acceso permitido");
				// Flujo de entrada para recibir el objeto totodile del cliente
	            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

	            // Flujo de salida para enviar datos al cliente
	            DataOutputStream salida2 = new DataOutputStream(cliente.getOutputStream());

	            // Recibir el objeto totodile del cliente
	            Totodile totodile = (Totodile) entradaObjeto.readObject();
	            System.out.println("Tu totodile se llama " + totodile.getNombre());

	            // Mandar mensaje al cliente
	            salida2.writeUTF("¿Quieres usar una Piedra agua en tu totodile " + totodile.getNombre() + "? (Y/N)");

	            // Flujo de entrada para recibir respuesta del cliente
	            DataInputStream entrada2 = new DataInputStream(cliente.getInputStream());
	            String respuestaCliente = entrada2.readUTF();

	            // Procesar la respuesta del cliente
	            if (respuestaCliente.equalsIgnoreCase("Y")) {
	                // Cambiar el nombre del Pikachu a "croconaw"
	            	totodile.setNombre("Crocronaw");
	                // Enviar el objeto croconaw evolucionado y un mensaje al cliente
	                ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
	                salidaObjeto.writeObject(totodile);
	                salidaObjeto.flush();
	                // Cerrar flujo de salida de objetos
	                salidaObjeto.close();
	            } else {
	                // Enviar un mensaje al cliente
	                salida2.writeUTF("Tú "+ totodile.getNombre()+" se queda igual");
	            }
			} else {
				salida.writeUTF("Acceso denegado");
			}

			// Cerrar flujos y socket
			salida.close();
			entrada.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para encriptar una cadena con SHA-256 y convertirla a hexadecimal
	private static byte[] encriptarSHA256(String contraseña) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(contraseña.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// CONVIERTE UN ARRAY DE BYTES A HEXADECIMAL
	static String Hexadecimal(byte[] resumen) {
		String hex = " ";
		for (int i = 0; i < resumen.length; i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				hex += "0";
			hex += h;
		}
		return hex.toUpperCase();
	}
}
