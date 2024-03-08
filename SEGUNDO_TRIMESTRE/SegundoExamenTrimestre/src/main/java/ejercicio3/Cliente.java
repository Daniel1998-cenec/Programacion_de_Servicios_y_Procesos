package ejercicio3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
    	 try {
         	Scanner sc=new Scanner(System.in);
             Socket socket = new Socket("localhost", 6001);
             System.out.println("CLASE CLIENTE.");

            
             DataInputStream entrada = new DataInputStream(socket.getInputStream());

            
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

             
             String mensajeServidor = entrada.readUTF();
             System.out.println("Servidor: " + mensajeServidor);

             
             String pass = sc.nextLine();

           
             salida.writeUTF(pass);

             
             String respuestaServidor = entrada.readUTF();
             System.out.println("Servidor: " + respuestaServidor);
             if(respuestaServidor.equals("Acceso permitido")) {
            	 
            	 Haunter haunter = new Haunter();

                 
                 ObjectOutputStream salidaObjeto = new ObjectOutputStream(socket.getOutputStream());
                 salidaObjeto.writeObject(haunter);
                 

                 
                 DataInputStream entrada2 = new DataInputStream(socket.getInputStream());
                 String mensajeServidor2 = entrada2.readUTF();
                 System.out.println("Servidor: " + mensajeServidor2);

                 
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 String respuesta = br.readLine();

                 
                 DataOutputStream salida2 = new DataOutputStream(socket.getOutputStream());
                 salida2.writeUTF(respuesta);

                
                 if (respuesta.equalsIgnoreCase("Y")) {
                     ObjectInputStream entradaObjeto = new ObjectInputStream(socket.getInputStream());
                     Haunter haunterEvolucionado = (Haunter) entradaObjeto.readObject();
                     System.out.println("¡Felicidades, tu "+haunter.getNombre()+" ha evolucionado a " + haunterEvolucionado.getNombre()+"!");
                     
                     System.out.println("Las estadisticas actuales de tu " + haunterEvolucionado.getNombre() + " son :\n"+
                             "Vida: "+ haunterEvolucionado.getVida()+"\n"+
                             "Ataque: "+ haunterEvolucionado.getAtaque()+"\n"+
                             "Defensa: "+ haunterEvolucionado.getDefensa());
                     
                     entradaObjeto.close();
                 } else {
                     
                     mensajeServidor2 = entrada2.readUTF();
                     System.out.println("Servidor: " + mensajeServidor2);
                 }
             }else {
            	 System.out.println("Las contraseñas no coinciden.");
             }

             
             salida.close();
             entrada.close();
             socket.close();
         } catch (IOException e) {
             System.err.println("Error: " + e.getMessage());
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
 }
