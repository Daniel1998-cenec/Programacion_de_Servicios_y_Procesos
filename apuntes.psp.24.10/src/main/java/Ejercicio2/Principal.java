package Ejercicio2;

import java.io.File;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		//Crea una bloc de nota del cdm del taklist
		
		ProcessBuilder pb=new ProcessBuilder ("CMD","/C","tasklist");
		
		//(ProcessBuilder pb=new ProcessBuilder ("CMD","/C","tasklist");
		//Hace lo mismo pero esta vez crea una nota del ipconfig
		
		File fOut=new File("Salida.txt");
		File fErr=new File("Error.txt");
		
		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		pb.start();
		
	}

}
