package Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Principal {
	public static void main(String[] args) throws IOException {
		
		//Abre lo que hay en la carpeta del ejercicio1. que en este caso abre el bloc de notas.
		
		File ruta=new File(".\\target\\classes");
		ProcessBuilder pb=new ProcessBuilder("java","ejercicio1.Principal");
		pb.directory(ruta);
		Process p;
		p=pb.start();
		InputStream is=p.getInputStream();
		int c;
		while((c=is.read())!=-1) {
			System.out.print((char)c);
		}
		is.close();
	}

}
