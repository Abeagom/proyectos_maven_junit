package com.endes.servicio;

import com.endes.dao.ProductDAO;
import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para ProductManagerImpl con Mockito y JUnit 5.
 */
class ProductManagerImplTest {

    private ProductDAO productDAOMock;
    private ProductManagerImpl productManager;

    @BeforeEach
    void setUp() {
        productDAOMock = mock(ProductDAO.class);  // ✅ Se crea el mock manualmente
        productManager = new ProductManagerImpl(productDAOMock);  // ✅ Se inyecta manualmente
    }

    @Test
    @DisplayName("Debe lanzar una excepción si el producto no existe")
    void shouldThrowExceptionWhenProductNotFound() throws ProductNotFoundException {
        // 1️⃣ Simular que el DAO lanza una excepción cuando se busca "NonExistent"
        doThrow(new ProductNotFoundException("No se encontró el producto"))
                .when(productDAOMock).findByName("NonExistent");

        // 2️⃣ Verificar que `findProduct` efectivamente lanza la excepción
        assertThrows(ProductNotFoundException.class, () -> productManager.findProduct("NonExistent"));

        // 3️⃣ Asegurar que se llamó a `findByName` exactamente una vez
        verify(productDAOMock, times(1)).findByName("NonExistent");
    }

    @Test
    @DisplayName("Debe encontrar un producto por su nombre")
    void shouldFindProductByName() throws ProductNotFoundException {
        Product mockProduct = new Product("Mouse", 25.99);
        when(productDAOMock.findByName("Mouse")).thenReturn(mockProduct);

        Product result = productManager.findProduct("Mouse");

        assertNotNull(result);
        assertEquals("Mouse", result.getName());
        assertEquals(25.99, result.getPrice());

        verify(productDAOMock, times(1)).findByName("Mouse");
    }

    @Test
    @DisplayName("Debe listar todos los productos correctamente")
    void shouldListAllProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product("Laptop", 1200.50),
                new Product("Phone", 699.99)
        );
        when(productDAOMock.findAll()).thenReturn(mockProducts);

        List<Product> result = productManager.listProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals(1200.50, result.get(0).getPrice());

        verify(productDAOMock, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe añadir un producto correctamente")
    void shouldAddProductSuccessfully() {
        doNothing().when(productDAOMock).insertProduct(any(Product.class));
        productManager.addProduct("Laptop", 1200.50);
        verify(productDAOMock, times(1)).insertProduct(any(Product.class));
    }
}
