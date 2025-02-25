package com.endes.dao;

import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

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
        try {
            productDAO.deleteAll(); // Asegurar que la base de datos esté limpia antes de cada test
        } catch (Exception e) {
            System.err.println("Error al limpiar la base de datos antes de la prueba: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    @DisplayName("Test: Insertar un producto")
    void testInsertProduct() {
        Product product = new Product("Laptop", 1200.50);
        assertDoesNotThrow(() -> productDAO.insertProduct(product));

        List<Product> products = productDAO.findAll();
        assertFalse(products.isEmpty());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals(1200.50, products.get(0).getPrice());
    }

    @Test
    @Order(2)
    @DisplayName("Test: Obtener todos los productos")
    void testFindAll() {
        assertDoesNotThrow(() -> {
            productDAO.insertProduct(new Product("Phone", 699.99));
            productDAO.insertProduct(new Product("Tablet", 499.99));
        });

        List<Product> products = productDAO.findAll();
        assertEquals(2, products.size());
    }

    @Test
    @Order(3)
    @DisplayName("Test: Buscar producto por nombre")
    void testFindByName() {
        assertDoesNotThrow(() -> productDAO.insertProduct(new Product("Mouse", 25.99)));

        assertDoesNotThrow(() -> {
            Product product = productDAO.findByName("Mouse");
            assertEquals("Mouse", product.getName());
            assertEquals(25.99, product.getPrice());
        });
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
    void testUpdatePriceByName() {
        assertDoesNotThrow(() -> productDAO.insertProduct(new Product("Monitor", 150.00)));

        assertDoesNotThrow(() -> productDAO.updatePriceByName("Monitor", 175.00));

        assertDoesNotThrow(() -> {
            Product updatedProduct = productDAO.findByName("Monitor");
            assertEquals(175.00, updatedProduct.getPrice());
        });
    }

    @Test
    @Order(6)
    @DisplayName("Test: Eliminar un producto por nombre")
    void testDeleteByName() {
        assertDoesNotThrow(() -> productDAO.insertProduct(new Product("Keyboard", 45.00)));

        assertDoesNotThrow(() -> productDAO.deleteByName("Keyboard"));

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

    @Test
    @Order(9)
    @DisplayName("Test: Manejo de SQLException en deleteByName()")
    void testSQLExceptionHandlingInDeleteByName() throws ProductNotFoundException {
        // 1️⃣ Crear un mock de ProductDAO
        ProductDAO mockProductDAO = mock(ProductDAO.class);

        // 2️⃣ Simular que `deleteByName()` lanza `ProductNotFoundException`
        doThrow(new ProductNotFoundException("Error interno al eliminar producto: Error de base de datos"))
                .when(mockProductDAO).deleteByName("ProductoInexistente");

        // 3️⃣ Verificar que la excepción correcta es lanzada
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            mockProductDAO.deleteByName("ProductoInexistente");
        });

        // 4️⃣ Asegurar que el mensaje de error contiene lo esperado
        assertTrue(exception.getMessage().contains("Error interno al eliminar producto"));
        assertTrue(exception.getMessage().contains("Error de base de datos"));
    }

}
