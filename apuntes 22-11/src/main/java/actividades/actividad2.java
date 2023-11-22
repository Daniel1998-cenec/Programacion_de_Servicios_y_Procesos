package actividades;

public class actividad2 extends Thread {

	private String mensaje;
	private int repeticiones;

	public actividad2(String mensaje, int repeticiones) {
		this.mensaje = mensaje;
		this.repeticiones = repeticiones;
	}

	public void run() {
		for (int i = 0; i < repeticiones; i++) {
			System.out.println(mensaje);
			try {
				Thread.sleep(500); // Pausa de 0.5 segundos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		actividad2 hiloTic = new actividad2("tic", 10);
		actividad2 hiloTac = new actividad2("tac", 10);

		hiloTic.start();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hiloTac.start();
	}

}
