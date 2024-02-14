package actividad;

import java.io.Serializable;

public class Pokemon implements Serializable{
	// Atributos
	private final String nombre;
	private float vida;
	private int nivel;
	
	// Constructor
	public Pokemon(String nombre, float vida, int nivel) {
		this.nombre = nombre;
		this.vida = vida;
		this.nivel = nivel;
	}

	public float getVida() {
		return vida;
	}

	public void setVida(float vida) {
		this.vida = vida;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return nombre;
	}
	
	 

}
