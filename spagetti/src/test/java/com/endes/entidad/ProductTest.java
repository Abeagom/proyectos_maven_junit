package com.endes.entidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Product sin AssertJ.
 */
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        // Se inicializa un producto antes de cada prueba
        product = new Product("Laptop", 1000.00);
    }

    @Test
    @DisplayName("Debe crear un producto con nombre y precio válidos")
    void shouldCreateProductSuccessfully() {
        Product product = new Product("Laptop", 1200.50);

        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals(1200.50, product.getPrice());
    }

    @ParameterizedTest
    @NullAndEmptySource //--> Probar automaticamente cadenas vacias
    @DisplayName("Debe lanzar IllegalArgumentException si el nombre es nulo o vacío")
    void shouldThrowExceptionForInvalidName(String invalidName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product(invalidName, 100));
        assertEquals("Error: Nombre inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException si el precio es negativo")
    void shouldThrowExceptionForNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product("Laptop", -10.00));
        assertEquals("Error: Precio negativo", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'Smartphone', 699.99",
            "'Tablet', 399.50",
            "'Monitor', 199.99"
    })
    @DisplayName("Debe permitir crear productos con diferentes nombres y precios válidos")
    void shouldCreateProductWithVariousValidValues(String name, double price) {
        Product product = new Product(name, price);

        assertNotNull(product);
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
    }

    @Test
    @DisplayName("Debe permitir cambiar el nombre de un producto válido")
    void shouldAllowChangingName() {
        product.setName("NewName");
        assertEquals("NewName", product.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Debe lanzar IllegalArgumentException al asignar nombre inválido")
    void shouldThrowExceptionForInvalidSetName(String invalidName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> product.setName(invalidName));
        assertEquals("Error: Nombre inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Debe permitir cambiar el precio a un valor válido")
    void shouldAllowChangingPrice() {
        product.setPrice(1200);
        assertEquals(1200, product.getPrice());
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException si el nuevo precio es negativo")
    void shouldThrowExceptionForNegativeSetPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> product.setPrice(-500));
        assertEquals("Error: Precio negativo", exception.getMessage());
    }

    @Test
    @DisplayName("Debe generar una representación toString válida")
    void shouldGenerateValidToString() {
        assertEquals("Product [name=Laptop, price=1000.0]", product.toString());
    }
}
