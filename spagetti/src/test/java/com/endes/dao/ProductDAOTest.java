package com.endes.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductDAOTest {
	
	private static ProductDAO productDAO;
	Product product1;
	Product product2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		productDAO = new ProductDAO();
		productDAO.crearTabla();
	}

	@BeforeEach
	void setUp() throws Exception {
		try {
			productDAO.deleteAll();
			product1 = new Product("PC", 1200.0);
			product2 = new Product("Pantalla", 300.0);
			
		}catch(Exception e) {
			System.out.println("error al borrar");
		}
		
	}

	@Test
	@Order(1)
	@DisplayName("Test: insertar un producto")
	void testInsertarProducto() {
		Product product = new Product("Laptop", 1200.3);
		assertDoesNotThrow(()-> productDAO.insertProduct(product));
		List<Product> productos = productDAO.findAll();
		assertEquals("Laptop", productos.get(0).getName(), "No se corresponde con el atributo name");
		assertEquals(1200.3, productos.get(0).getPrice(), "El precio no se corresponde");
	}
	@Test
	@Order(2)
	@DisplayName("Test : Obtener todos los productos")
	void testFindAll() {
		assertDoesNotThrow(()->{
			productDAO.insertProduct(product1);
			productDAO.insertProduct(product2);
		});
		List<Product> productos = productDAO.findAll();
		assertEquals(2, productos.size());
	}
	
	@Test
	@Order(3)
	@DisplayName("Test : Buscar producto por nombre")
	void testFindByName() {
		assertDoesNotThrow(()-> productDAO.insertProduct(product1));
		assertDoesNotThrow(()-> {
			Product product = productDAO.findByName("PC");
				assertEquals("PC", product.getName());
				assertEquals(1200.0, product.getPrice());
			
			});
	}
	
	@Test
	@Order(4)
	@DisplayName("Test :: Buscar producto inexistentes lanza excepción")
	void testFindByNameNotFound() {
		assertThrows(ProductNotFoundException.class, ()->productDAO.findByName("No exist"));
	}
	
	

}
