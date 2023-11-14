package ejercicio4;

import java.io.IOException;
import java.io.InputStream;

public class Principal {

	public static void main(String[] args) throws IOException {

		ProcessBuilder processBuilder = new ProcessBuilder("cmd","/c","tasklist");
		
		Process proceso = processBuilder.start();
		InputStream is=proceso.getInputStream();
		int c;
		while ((c=is.read()) != -1) {
			System.out.print((char)c);
		}
		is.close();
	}

}
