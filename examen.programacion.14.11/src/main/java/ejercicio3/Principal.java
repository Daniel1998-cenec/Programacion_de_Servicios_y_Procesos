package ejercicio3;

import java.io.IOException;
import java.io.InputStream;

public class Principal {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","chrome");
		//ProcessBuilder pb = new ProcessBuilder("cmd","/c","tasklist");
		
		Process p = pb.start();
		InputStream is=p.getInputStream();
		int c;
		while ((c=is.read()) != -1) {
			System.out.print((char)c);
		}
		is.close();
	}

}
