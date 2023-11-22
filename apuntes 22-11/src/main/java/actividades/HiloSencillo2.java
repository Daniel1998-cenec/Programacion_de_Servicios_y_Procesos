package actividades;

public class HiloSencillo2 extends Thread {

	public void run() {
		for (byte a = 0; a < 10; a++) {
			System.out.println("Hola, soy un hilo ejecutandose " + (a + 1) + " veces");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSencillo2 a= new HiloSencillo2();
		a.start();
	}
}