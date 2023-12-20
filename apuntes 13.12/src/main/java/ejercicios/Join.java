package ejercicios;

public class Join extends Thread {

	public static void main(String[] args) {

		Arriba h1 = new Arriba();
		Abajo h2 = new Abajo();
		
		h1.setName("Hilo1");
		h2.setName("Hilo2");
		
		h1.start();
		h2.start();
		
		System.out.println("El Hilo se encuentra en estado " + h1.getState());
		System.out.println("El Hilo se encuentra en estado " + h2.getState());
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // catch
		System.out.println("\nEL PROGRAMA HA FINALIZADO");
		
		System.out.println("El Hilo se encuentra en estado " + h1.getState());
		System.out.println("El Hilo se encuentra en estado " + h2.getState());
			
	} // main
} // class

class Arriba extends Thread {

	public void run() {
		for (int i = 0; i <= 100; i++) {
			System.out.println(getName() + " cuenta " + i);
		} // for
		System.out.println("Fin de " + getName());
	} // run
} // class

class Abajo extends Thread {

	public void run() {
		for (int i = 100; i >= 0; i--) {
			System.out.println(getName() + " cuenta " + i);
		} // for
		System.out.println("Fin de " + getName());
	} // run
} // class