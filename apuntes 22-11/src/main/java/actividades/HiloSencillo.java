package actividades;

public class HiloSencillo extends Thread {
	
	public void run() {
		for(byte i=0;i<10;i++) {
			System.out.println("Hola, soy un hilo ejecutandose "+(i+1)+" veces");
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSencillo h= new HiloSencillo();
		h.start();
		
		
	}

}
