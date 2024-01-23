package apuntes2301;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteRed {

	public static void main(String[] args) {
		Socket cliente = null;

		try {
			cliente = new Socket("localhost", 62000);
			ObjectOutputStream oos=new ObjectOutputStream(cliente.getOutputStream());
			oos.writeObject("Hola, servidor. Soy un cliente.");
			System.out.println("Fin del mensaje");
			
			InputStream input = cliente.getInputStream();// recibe entrada de bajo nivel
			ObjectInputStream ois = new ObjectInputStream(input);// transforma ese bajo nivel a objetos
			String cadenaLeida = (ois.readObject()).toString();// transforma el objeto en una cadena de texto y lo lee.
			System.out.println("El servidor dice: " + cadenaLeida);

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
