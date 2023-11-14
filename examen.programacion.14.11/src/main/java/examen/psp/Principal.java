package examen.psp;

import java.io.IOException;
import java.io.InputStream;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessBuilder pb=new ProcessBuilder ("Chrome");
		try {
			Process p=pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
