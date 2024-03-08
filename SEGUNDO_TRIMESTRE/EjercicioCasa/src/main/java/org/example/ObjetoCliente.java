package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class ObjetoCliente {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 6000;
		Pokemon pokemon = new Pokemon("Magmar", "Magmatizador",100,120);

		try {
			Socket cliente = new Socket(host, puerto);

			ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

			System.out.println("Nombre:" + pokemon.pkName + " - Obj:" + pokemon.pkObject + " - Atk:" + pokemon.pkAtk + " - Def:" + pokemon.pkDef);
			System.out.println("¿Seguro que quieres intercambiar tu " + pokemon.pkName + "? (si/no)");
			String respuesta = sc.nextLine();
			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

			oos.writeObject(respuesta);
			if (respuesta.equals("si")) {
				oos.writeObject(pokemon);

				pokemon = (Pokemon) ois.readObject();

				System.out.println("Has recibido un: Nombre:" + pokemon.pkName + " - Obj:" + pokemon.pkObject + " - Atk:" + pokemon.pkAtk + " - Def:" + pokemon.pkDef);
			} else {
				System.out.println("Que gay!");
			}

			ois.close();
			oos.close();
			cliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}