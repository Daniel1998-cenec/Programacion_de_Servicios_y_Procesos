package ejercicioObjeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjetoServidor {

	public static void main(String[] args) {

		ServerSocket servidor;
		int puerto = 6000;
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando al cliente en el puerto: " + servidor.getLocalPort());
			Socket cliente = servidor.accept();

			ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

			Empleados empleado = new Empleados("Alberto", 3000);

			oos.writeObject(empleado);

			System.out.println("Envío: " + empleado.getNombre() + " con sueldo mensual de " + empleado.getSueldo());

			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

			Empleados empleado1 = (Empleados) ois.readObject();
			
			System.out.println("Se ha recibido el empleado " + empleado.getNombre() + " con la modificación del sueldo " + empleado1.getSueldo() + "€.");
			
			oos.close();
			ois.close();
			cliente.close();
			servidor.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} // catch
	} // main
} // class