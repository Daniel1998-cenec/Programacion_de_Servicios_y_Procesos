package ejercicio6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws IOException {

		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "DATE");

		Process p = pb.start();
		InputStream is = p.getInputStream();
		OutputStream os = p.getOutputStream();

		int c;
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Escribe algo y presiona Enter para finalizar:");
		String userInput = scanner.nextLine();

		// Escribe la entrada del usuario en el proceso
		os.write(userInput.getBytes());
		os.write('\n');
		os.flush();
		is.close();

		// Espera a que el proceso termine
		try {
			int exitCode = p.waitFor();
			System.out.println("Proceso terminado con código de salida: " + exitCode);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Quiero poder escribir en la consola y el programa termina.
	}

}
