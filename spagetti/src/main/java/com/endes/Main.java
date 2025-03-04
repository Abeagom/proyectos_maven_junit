package com.endes;

import com.endes.dao.ProductDAO;
import com.endes.entidad.Product;
import com.endes.servicio.ProductManager;
import com.endes.servicio.ProductManagerImpl;
import com.github.javafaker.Faker;

public class Main {
/**
 * # NOTA #
 * Posible problema de conexión: 
 * + ProductDAO + parece abrir conexiones sin cerrarlas correctamente.
 */
	public static void main(String[] args) {
		
		eliminar();
	}
	
	public static void eliminar() {
		 ProductDAO productDAO = new ProductDAO();
		 productDAO.deleteAll();
		 productDAO.close(); 
	}

	public static void agregar() {
		ProductDAO productDAO = new ProductDAO();

	    // Crear tabla
	    productDAO.crearTabla();

	    
	    // Insertar producto sin cerrar la conexión antes
	    Faker faker = new Faker();
		for(int i=0; i<100; i++) {
			 productDAO.insertProduct(new Product(faker.commerce().productName(), faker.number().numberBetween(3, 199)));

		}
		
		 productDAO.close(); 
	}

}
