package examen2;

import segundo.examen.primer.trimestre.Main;

class Descarga1 extends Thread implements Runnable{
	
	private String nombre;

    public void MultihilosExamen(String nombre) {
        this.nombre = nombre;
    }
	
	public void run() {
		for (int i = 0; i <= 100; i++) {
			System.out.println(nombre + ": " + i + "% completado.");
		}
		System.out.println("\nFin de la descarga "+nombre + "% completado.");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Descarga2 extends Thread implements Runnable{
	
	private String nombre;

    public void MultihilosExamen(String nombre) {
        this.nombre = nombre;
    }
	public void run() {
		for (int i = 0; i <= 100; i += 2) {
			System.out.println(nombre + ": " + i + "% completado.");
		}
		System.out.println("\nFin de la descarga "+nombre + "% completado.");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Descarga3 extends Thread implements Runnable{
	
	private String nombre;

    public void MultihilosExamen(String nombre) {
        this.nombre = nombre;
    }
	public void run() {
		for (int i = 0; i <= 100; i += 3) {
			System.out.println(nombre + ": " + i + "% completado.");
		}
		System.out.println("\nFin de la descarga "+nombre+ "% completado.");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class MultihilosExamen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Main descarga1 = new Main("Descarga 1");
	     Main descarga2 = new Main("Descarga 2");
	     Main descarga3 = new Main("Descarga 3");

	        
	     Thread hilo1 = new Thread(descarga1);
	     Thread hilo2 = new Thread(descarga2);
	     Thread hilo3 = new Thread(descarga3);
		
	     hilo1.setName("Descarga 1");
	     hilo2.setName("Descarga 2");
	     hilo3.setName("Descarga 3");
	     hilo1.setPriority(1);
	     hilo2.setPriority(2);
	     hilo3.setPriority(3);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		
		
		
	}

}
