package Ejercicio5;

import java.io.File;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder ("CMD","/C","tasklist");
		File fOut=new File("Salida.txt");
		File fErr=new File("Error.txt");
		
		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		pb.start();

	}

}
