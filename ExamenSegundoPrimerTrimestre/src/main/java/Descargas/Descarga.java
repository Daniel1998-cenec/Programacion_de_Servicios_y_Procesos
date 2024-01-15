package Descargas;

class Descarga1 extends Thread implements Runnable{
	public void run() {
		for (int i = 0; i <= 100; i++) {
			System.out.println(getName() + ": " + i + "% completado.");
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\nFin de la "+getName());
	}
}

class Descarga2 extends Thread implements Runnable{
	public void run() {
		for (int i = 0; i <= 100; i += 2) {
			System.out.println(getName() + ": " + i + "% completado.");
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\nFin de la "+getName());
	}
}

class Descarga3 extends Thread implements Runnable{
	public void run() {
		for (int i = 0; i <= 100; i += 3) {
			System.out.println(getName() + ": " + i + "% completado.");
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\nFin de la "+getName());
	}
}

public class Descarga {

	public static void main(String[] args) {
		System.out.println("Descarga 1 iniciada");
		System.out.println("Descarga 2 iniciada");
		System.out.println("Descarga 3 iniciada");
		System.out.println();
		// TODO Auto-generated method stub
		Descarga1 h1=new Descarga1();
		Descarga2 h2=new Descarga2();
		Descarga3 h3=new Descarga3();
		
		h1.setName("Descarga 1");
		h2.setName("Descarga 2");
		h3.setName("Descarga 3");
		
		h1.setPriority(5);
		h2.setPriority(Thread.MIN_PRIORITY);
		h3.setPriority(Thread.MAX_PRIORITY);
		
		h1.start();
		h2.start();
		h3.start();
		
        
        try { 
        	h1.join();
        	h2.join();
        	h3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("La descarga de los tres hilos ha terminado");
	}

}
