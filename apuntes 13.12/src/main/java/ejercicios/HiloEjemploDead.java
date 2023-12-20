package ejercicios;

public class HiloEjemploDead extends Thread {
	   boolean stopHilo = false;
	   public void pararHilo() {
	      stopHilo = true;
	   }
	   public void run() {
	      while (!stopHilo) {
	         System.out.println("El hilo se está ejecutando.");
	      }
	   }
	   public static void main(String[] args) {
	      HiloEjemploDead h = new HiloEjemploDead();
	      h.start();
	      try {
	         Thread.sleep(1000);
	      } catch (InterruptedException e) {
	      e.printStackTrace();
	      }
	      h.pararHilo();
	   }
	}
