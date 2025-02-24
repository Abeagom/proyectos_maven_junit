package com.endes.entidad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Pruebas unitarias para la entidad Product.
 */
class ProductTest {
    
    private Product product;
    
    @BeforeEach
    void setUp() {
        product = new Product(1L, "Laptop", 1500.00);
    }
    
    @Test
    @DisplayName("Verificar la correcta creación del objeto Product")
    void testProductCreation() {
        assertNotNull(product);
        assertEquals(1, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(1500.00, product.getPrice());
    }
    
    @Test
    @DisplayName("Probar getters y setters")
    void testGettersAndSetters() {
        product.setId(2L);
        product.setName("Smartphone");
        product.setPrice(799.99);
        
        assertEquals(2, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals(799.99, product.getPrice());
    }
    
    @Test
    @DisplayName("Probar equals y hashCode")
    void testEqualsAndHashCode() {
        Product sameProduct = new Product(1L, "Laptop", 1500.00);
        Product differentProduct = new Product(2L, "Tablet", 499.99);
        
        assertEquals(product, sameProduct);
        assertNotEquals(product, differentProduct);
        
        assertEquals(product.hashCode(), sameProduct.hashCode());
        assertNotEquals(product.hashCode(), differentProduct.hashCode());
    }
    
    @Test
    @DisplayName("Probar toString")
    void testToString() {
        String expectedString = "Product{id=1, name='Laptop', price=1500.0}";
        assertEquals(expectedString, product.toString());
    }
}
