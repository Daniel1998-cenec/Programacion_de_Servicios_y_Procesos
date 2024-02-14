package actividad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjetoCliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try {
            Socket cliente = new Socket(host, puerto);
            System.out.println("Conectado con Servidor en el puerto: " + cliente.getPort());

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

            Pokemon pokemon = (Pokemon) ois.readObject();
            System.out.println("Se ha recibido al pokemon " + pokemon.getNombre());

            System.out.print("El entrenador pokemon va a luchar (Y/N): ");
            // Assuming user input here, you can modify it according to your input mechanism.
            String respuesta = "Y"; // Replace this line with actual user input.

            oos.writeObject(respuesta);

            Object response = ois.readObject();
            
            int subidaNivel = pokemon.getNivel() + 1;

            if (response instanceof Pokemon) {
                pokemon = (Pokemon) response;
                if (pokemon.getVida() == 0) {
                    System.out.println("Has perdido. " + pokemon.getNombre() + " tiene vida = 0");
                } else {
                    System.out.println("Has ganado. " + pokemon.getNombre() + "\nCharmander acaba de subir al nivel " + subidaNivel);
                }
            } else if (response instanceof String) {
                String message = (String) response;
                System.out.println(message);
            }

            ois.close();
            oos.close();
            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}