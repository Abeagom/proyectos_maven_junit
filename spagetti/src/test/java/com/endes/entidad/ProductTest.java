package com.endes.entidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTest {
	
	private Product product;

	@BeforeEach
	void setUp() throws Exception {
		product = new Product("Laptop", 1200.00);
	}

	@Test
	@DisplayName("Debe crear un producto con nombre y precio válido")
	void deberiaCrearProductoValido() {
	
		String nameEsperado = "Laptop";
		Double priceEsperado = 1200.00;
		
		// No es nulo
		assertNotNull(product);
		//Comparamos
		assertEquals(nameEsperado, product.getName());
		assertEquals(priceEsperado, product.getPrice());
	}
	
	@Test
	@DisplayName("Debe lanzar illegalArgumentException si el nombre es nulo o vacio")
	void deberiaLanzarExcepcionPorIllegalArgument() {
		Exception exception = assertThrows(IllegalArgumentException.class, ()->new Product(null, 3.2));
		String mensajeEsperado = "Error: Nombre inválido";
		
		assertEquals(mensajeEsperado, exception.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource({"'Smartphone', 699.99", "'Tablet', 345.50", "'Monitor', 100.00"})
	@DisplayName("Debe permitir crear productos con diferentes nobmres y precios validos")
	void deberiaCrearProductoConVariosAtributosValidos(String name, double price) {
		Product p = new Product(name, price);
		assertNotNull(p);
		assertEquals(name, p.getName());
		assertEquals(price, p.getPrice());
	}
}
