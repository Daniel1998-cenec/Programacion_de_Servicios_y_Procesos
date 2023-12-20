package apuntes29.nov;

class Hilo implements Runnable{
	
	public Hilo() {
		
	}
	
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hola, soy un hilo ejecutándose con Runnable");
	}
	
}

public class Runable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hilo h1= new Hilo();
		new Thread(h1).start();
	}

}
