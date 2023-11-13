package ejercicio1;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		//Abre el bloc de notas
		
		ProcessBuilder pb=new ProcessBuilder("NotePad");
		//ProcessBuilder pb=new ProcessBuilder("calc");
		
		Process p=pb.start();
		
	}

}
