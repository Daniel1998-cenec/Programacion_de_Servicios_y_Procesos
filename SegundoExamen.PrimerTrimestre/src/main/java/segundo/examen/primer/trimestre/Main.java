package segundo.examen.primer.trimestre;

public class Main  extends Thread implements Runnable{
	
    private String nombre;

    public Main(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
    	
        System.out.println("Descarga " + nombre + " iniciada.");

        for (int i = 0; i <= 100; i += 10) {
            System.out.println(nombre + ": " + i + "% completado.");
            
            try {
            	
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Descarga " + nombre + " completada.");
    }
    
    public static void main(String[] args) {
        
        Main descarga1 = new Main("Descarga 1");
        Main descarga2 = new Main("Descarga 2");
        Main descarga3 = new Main("Descarga 3");

        
        Thread hilo1 = new Thread(descarga1);
        Thread hilo2 = new Thread(descarga2);
        Thread hilo3 = new Thread(descarga3);

        
        
    }
}