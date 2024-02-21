package ejercicio2;

import java.io.Serializable;

public class Totodile implements Serializable {
	private String nombre;

	public Totodile() {
		nombre = "Salmón";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}