package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ObjetoServidor {

	public static void main(String[] args) {
		int puerto = 6000;
		ServerSocket servidor;

		try {
			servidor = new ServerSocket(puerto);

			System.out.println("Esperando al cliente en el puerto: " + servidor.getLocalPort());
			Socket cliente = servidor.accept(); // Espera una petición de un cliente, el main se detiene.

			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

			String respuesta = (String) ois.readObject();
			if (respuesta.equals("si")) {
				Pokemon pokemon = (Pokemon) ois.readObject();

				String nName = "Magmortar";
				System.out.println(pokemon.pkName + " esta evolucionando en " + nName);
				pokemon.pkName = nName;
				pokemon.pkObject = "";
				pokemon.pkAtk = pokemon.pkAtk+20;
				pokemon.pkDef = pokemon.pkDef+20;
				System.out.println("Nombre:" + pokemon.pkName + " - Obj:" + pokemon.pkObject + " - Atk:" + pokemon.pkAtk + " - Def:" + pokemon.pkDef);

				oos.writeObject(pokemon);
			}

			ois.close();
			cliente.close();
			servidor.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
