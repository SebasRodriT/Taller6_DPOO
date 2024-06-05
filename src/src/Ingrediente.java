package src;

public class Ingrediente {
	
	private String nombre;
	private boolean vegano;
	
	public Ingrediente(String pnombre, boolean pvegano) {
		
		this.nombre = pnombre;
		this.vegano = pvegano;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}
}
