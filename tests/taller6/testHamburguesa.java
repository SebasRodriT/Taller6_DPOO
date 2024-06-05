package taller6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import src.Hamburguesa;
import src.Ingrediente;

public class testHamburguesa {

	/*A:
	 Crear rebaja:
		Pruebe que la rebaja se cree bien para porcentajes de menos de 30
		Pruebe que la rebaja se cree bien para porcentajes de 30 o más
	 */
	
	
	Hamburguesa hamb = new Hamburguesa("CangreBurguer",10000); 
	Ingrediente ingre1 = new Ingrediente("Tomate", true);
	Ingrediente ingre2 = new Ingrediente("Carne Angus", false);
	Ingrediente ingre3 = new Ingrediente("Queso Cheddar", false);
	Ingrediente ingre4 = new Ingrediente("Lechuga", true);
	Ingrediente ingre5 = new Ingrediente("Pepinillos", true);
	Ingrediente ingre6 = new Ingrediente("Ketchup", true);
	Ingrediente ingre7 = new Ingrediente("Jamón", false);
	
	//Porcentajes de menos de 30
	@Test
	public void testCrearRebajaMenos30() {
		hamb.crear_rebaja(20); // Aplicar un 20% de rebaja
        assertEquals(8000, hamb.getPrecio()); // 1000 - 20% = 8000
	}
	
	//Porcentajes de 30 o mas
	//Porcentaje de 30
	@Test
	public void testCrearRebaja30( ) {
		hamb.crear_rebaja(30); // Aplicar un 30% de rebaja
        assertEquals(7000, hamb.getPrecio()); // 1000 - 30% = 7000
	}
	
	//Porcentajes de más de 30
	@Test
	public void testCrearRebajaMas30( ) {
		hamb.crear_rebaja(50); // Aplicar un 30% de rebaja
        assertEquals(7000, hamb.getPrecio()); // 1000 - 30% = 7000
	}
	
	/*B:
	 Aumentar precio:
		Pruebe que el precio se aumente correctamente
		Pruebe que la excepción se lance correctamente cuando estén muy careros
	 */
	
	@Test
    public void testAumentarPrecioCorrectamente() {
        try {
            hamb.aumentar_precio(12000); // Incrementa el precio en un 20%
            assertEquals(12000, hamb.getPrecio()); // El precio debería ser 12000
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void testAumentarPrecioExcesivoLanzaExcepcion() {
        Exception exception = assertThrows(Exception.class, () -> {
            hamb.aumentar_precio(20000); // Intentar aumentar el precio más del 20%
        });

        assertEquals("101 dólares por una cangreburguer!?", exception.getMessage());
    }
    
    /*C:
     Agregar Ingrediente:

		Pruebe que al agregar, el ingrediente en cuestión quede en los ingredientes
		Pruebe que el tamaño cambia en 1 al agregar 1
		Pruebe que todo falla si se trata de agregar un 7mo ingrediente.
     */
    @Test
    public void testAgregarIngrediente() {
        try {
            hamb.agregar_ingrediente(ingre1);
            assertTrue(hamb.getIngredientes().contains(ingre1), "El ingrediente debería estar en la lista");
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar un ingrediente");
        }
    }
    
    @Test
    public void testTamanoCambiaAlAgregar() {
        try {
            int tamanoInicial = hamb.getIngredientes().size();
            hamb.agregar_ingrediente(ingre1);
            assertEquals(tamanoInicial + 1, hamb.getIngredientes().size(), "El tamaño de la lista debería aumentar en 1");
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar un ingrediente");
        }
    }
    
    @Test
    public void testAgregarSeptimoIngredienteLanzaExcepcion() {
        try {        	
        	hamb.agregar_ingrediente(ingre1);
        	hamb.agregar_ingrediente(ingre2);
        	hamb.agregar_ingrediente(ingre3);
        	hamb.agregar_ingrediente(ingre4);
        	hamb.agregar_ingrediente(ingre5);
        	hamb.agregar_ingrediente(ingre6);
            
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar hasta 6 ingredientes");
        }

        Exception exception = assertThrows(Exception.class, () -> {
        	hamb.agregar_ingrediente(ingre7);
        });

        assertEquals("Esto no es Burgermaster, muchos ingredientes", exception.getMessage());
    }
    
    /*
     D:
     Determinar Vegana:

		Pruebe en una hamburguesa con solo ingredientes veganos
		Pruebe en una hamburguesa sin ningún ingrediente vegano
		Prueba en una hamburguesa con varios ingredientes veganos y no veganos
     */
    
    @Test
    public void testHamburguesaSoloIngredientesVeganos() {
        try {
            hamb.agregar_ingrediente(new Ingrediente("Lechuga", true));
            hamb.agregar_ingrediente(new Ingrediente("Tomate", true));
            hamb.agregar_ingrediente(new Ingrediente("Pepino", true));
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar ingredientes veganos");
        }
        
        assertTrue(hamb.determinar_vegana(), "La hamburguesa debería ser vegana");
    }

    @Test
    public void testHamburguesaSinIngredientesVeganos() {
        try {
        	hamb.agregar_ingrediente(new Ingrediente("Queso", false));
        	hamb.agregar_ingrediente(new Ingrediente("Carne", false));
        	hamb.agregar_ingrediente(new Ingrediente("Bacon", false));
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar ingredientes no veganos");
        }
        
        assertFalse(hamb.determinar_vegana(), "La hamburguesa no debería ser vegana");
    }

    @Test
    public void testHamburguesaIngredientesMixtos() {
        try {
        	hamb.agregar_ingrediente(new Ingrediente("Lechuga", true));
        	hamb.agregar_ingrediente(new Ingrediente("Tomate", true));
        	hamb.agregar_ingrediente(new Ingrediente("Queso", false));
        	hamb.agregar_ingrediente(new Ingrediente("Pepino", true));
        } catch (Exception e) {
            fail("No debería lanzar excepción al agregar ingredientes mixtos");
        }
        
        assertFalse(hamb.determinar_vegana(), "La hamburguesa no debería ser vegana");
    }
}
