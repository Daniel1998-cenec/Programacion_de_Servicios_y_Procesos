package actividades;

public class Actividad1 extends Thread {
	
	public void run() {
        for (int i = 2; i <= 20; i += 2) {
        	System.out.println("Hola, soy el hilo "+ getName());
        }
    }

	class ImprimeImpares extends Thread {
	    public void run() {
	        for (int y = 1; y <= 20; y += 2) {
	        	System.out.println("Hola, soy el hilo "+ getName());
	        }
	    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Actividad1 hiloPares = new Actividad1();
		Actividad1 hiloImpares = new Actividad1();

		hiloPares.setName("par");
		hiloImpares.setName("impar");
		
        // Iniciar los hilos
        hiloPares.start();
        hiloImpares.start();
	}

}
