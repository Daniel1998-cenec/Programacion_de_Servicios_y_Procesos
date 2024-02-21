package actividad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ObjetoServidor {

    public static void main(String[] args) {
        ServerSocket servidor;
        int puerto = 6000;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando al entrenador en pokemon para entrar al combate: " + servidor.getLocalPort());
            Socket cliente = servidor.accept();

            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            Pokemon pokemon = new Pokemon("Charmander", 100.0f, 25);
            oos.writeObject(pokemon);

            System.out.println("Envío: " + pokemon.getNombre() + " con vida " + pokemon.getVida() + " con un nivel " + pokemon.getNivel());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrenador Gary quiere luchar (Y/N): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("N")) {
                oos.writeObject("Eres un cagao.");
                cliente.close();
                servidor.close();
                return;
            } else if (respuesta.equalsIgnoreCase("Y")) {
                int randomNum = new Random().nextInt(100+1);
                if (randomNum < 50) {
                    pokemon.setVida(0);
                    oos.writeObject(pokemon);
                    System.out.println("Has perdido. " + pokemon.getNombre() + " tiene vida = 0");
                } else {
                    pokemon.setNivel(pokemon.getNivel() + 1);
                    oos.writeObject(pokemon);
                    System.out.println("Has ganado. " + pokemon.getNombre() + " tiene nivel +" + pokemon.getNivel());
                }
            }

            oos.close();
            ois.close();
            cliente.close();
            servidor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}