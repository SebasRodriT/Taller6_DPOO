package src;

import java.util.ArrayList;

public class Hamburguesa {
	
	private ArrayList<Ingrediente> ingredientes;
	private String nombre;
	private int precio;
	
	public Hamburguesa(String pnombre, int pprecio) {
		this.nombre = pnombre;
		this.precio = pprecio;
		ingredientes = new ArrayList<Ingrediente>();
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	//TODO punto 6 opcion a
	
	public void crear_rebaja (double porcentaje) {
		if (porcentaje > 30) {
			porcentaje = 30;
		}
		
		porcentaje = porcentaje / 100.0;
		
		this.precio = (int) (((double) this.precio)*(1.0-porcentaje)); 
	}
	
	//TODO punto 6 opcion b
	
	public void aumentar_precio(int nprecio) throws Exception{
		if (nprecio > (int) (((double) this.precio)*1.2)){
			throw new Exception("101 dólares por una cangreburguer!?");
		}
		this.precio = (int) nprecio;
	}
	
	//TODO punto 7 opcion a
	
	public void agregar_ingrediente(Ingrediente ingrediente) throws Exception {
		if (ingredientes.size() > 5) {
			throw new Exception("Esto no es Burgermaster, muchos ingredientes");
		}
		ingredientes.add(ingrediente);
	}
	
	//TODO punto 7 opcion b
	
	public boolean determinar_vegana() {
		boolean es_vegana = true;
		for(Ingrediente ing: ingredientes) {
			es_vegana = es_vegana && ing.isVegano();
		}
		return es_vegana;
	}

}
