package actividades;

public class HiloSencillo3 extends Thread{

	public void run() {
		for (byte i=0; i<10; i++) {
			System.out.println("Hola, soy el hilo "+ getName()+ " ejecuntandose "+ (i+1)+" veces.");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSencillo3 h1=new HiloSencillo3();
		HiloSencillo3 h2=new HiloSencillo3();
		
		h1.start();
		h2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		h1.setName("hilo 1");
		h2.setName("hilo 2");
	}

}
