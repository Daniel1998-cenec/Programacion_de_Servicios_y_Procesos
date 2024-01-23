package apuntes1601;

import java.io.IOException;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String host= "LocalHost";
		int puerto=2026;
		
		Socket cliente = new Socket(host,puerto);
		
		System.out.println("hola");
		
		cliente.close();
		
	}

}
