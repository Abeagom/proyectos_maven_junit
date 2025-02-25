package com.endes.dao;
import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase ProductDAO usando JUnit 5.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Permite ejecutar pruebas en orden específico
public class ProductDAOTest {

    private static ProductDAO productDAO;

    @BeforeAll
    static void setup() {
        productDAO = new ProductDAO();
        productDAO.crearTabla();
    }

    @BeforeEach
    void cleanDatabase() {
       productDAO.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Test: Insertar un producto")
    void testInsertProduct() {
        Product product = new Product("Laptop", 1200.50);
        productDAO.insertProduct(product);
        List<Product> products = productDAO.findAll();
        assertFalse(products.isEmpty());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals(1200.50, products.get(0).getPrice());
    }

    @Test
    @Order(2)
    @DisplayName("Test: Obtener todos los productos")
    void testFindAll() {
        productDAO.insertProduct(new Product("Phone", 699.99));
        productDAO.insertProduct(new Product("Tablet", 499.99));
        List<Product> products = productDAO.findAll();
        assertEquals(2, products.size());
    }

    @Test
    @Order(3)
    @DisplayName("Test: Buscar producto por nombre")
    void testFindByName() throws ProductNotFoundException {
        productDAO.insertProduct(new Product("Mouse", 25.99));
        Product product = productDAO.findByName("Mouse");
        assertEquals("Mouse", product.getName());
        assertEquals(25.99, product.getPrice());
    }

    @Test
    @Order(4)
    @DisplayName("Test: Buscar producto inexistente lanza excepción")
    void testFindByNameNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productDAO.findByName("NonExistent"));
    }

    @Test
    @Order(5)
    @DisplayName("Test: Actualizar precio de un producto")
    void testUpdatePriceByName() throws ProductNotFoundException {
        productDAO.insertProduct(new Product("Monitor", 150.00));
        productDAO.updatePriceByName("Monitor", 175.00);
        Product updatedProduct = productDAO.findByName("Monitor");
        assertEquals(175.00, updatedProduct.getPrice());
    }

    @Test
    @Order(6)
    @DisplayName("Test: Eliminar un producto por nombre")
    void testDeleteByName() throws ProductNotFoundException {
        productDAO.insertProduct(new Product("Keyboard", 45.00));
        productDAO.deleteByName("Keyboard");
        assertThrows(ProductNotFoundException.class, () -> productDAO.findByName("Keyboard"));
    }

    @Test
    @Order(7)
    @DisplayName("Test: Intentar actualizar producto inexistente lanza excepción")
    void testUpdateNonExistentProduct() {
        assertThrows(ProductNotFoundException.class, () -> productDAO.updatePriceByName("NonExistent", 100.00));
    }

    @Test
    @Order(8)
    @DisplayName("Test: Intentar eliminar producto inexistente lanza excepción")
    void testDeleteNonExistentProduct() {
        assertThrows(ProductNotFoundException.class, () -> productDAO.deleteByName("NonExistent"));
    }
}
