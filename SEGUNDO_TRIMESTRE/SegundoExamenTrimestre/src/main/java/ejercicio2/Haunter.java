package ejercicio2;

import java.io.Serializable;

public class Haunter implements Serializable {
	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;

	public Haunter() {
		nombre = "Haunter";
		vida=80;
		ataque=20;
		defensa=30;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	
	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
}
