package ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(6001);
			System.out.println("Servidor esperando conexiones...");

			Socket cliente = servidor.accept();
			System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostName());

			
			String contraseñaAlmacenada = "cenec";
			
			String contraseñaEncriptada = Hexadecimal(encriptarSHA256(contraseñaAlmacenada));

			
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

			
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());

			
			salida.writeUTF("Ingrese la contraseña:");

			
			String contraseñaCliente = entrada.readUTF();

			
			String contraseñaClienteEncriptada = Hexadecimal(encriptarSHA256(contraseñaCliente));

			
			if (contraseñaEncriptada.equals(contraseñaClienteEncriptada)) {
				
				salida.writeUTF("Acceso permitido");


	            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

	            
	            DataOutputStream salida2 = new DataOutputStream(cliente.getOutputStream());

	            
	            Haunter haunter = (Haunter) entradaObjeto.readObject();
	            System.out.println("Tu pokemon elegido es un " + haunter.getNombre());

	      
	            salida2.writeUTF("¿Quieres que tu "+ haunter.getNombre() +" evolucione a Gengar? (Y/N)");

	            
	            DataInputStream entrada2 = new DataInputStream(cliente.getInputStream());
	            String respuestaCliente = entrada2.readUTF();

	            // Procesar la respuesta del cliente
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
	                salida2.writeUTF("Que lastima");
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