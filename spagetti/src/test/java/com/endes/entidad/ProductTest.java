package com.endes.entidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.endes.CuentaBancaria;

class ProductTest {
	Product p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Product ("Teclado", 20);
	}

	@Test
	@DisplayName("Test del constructor válido")
	void testConstructorValido() {
		String resultadoEsperado = "Teclado";
		double precioEsperado = 20;
		assertEquals(resultadoEsperado, p.getName(), "El nombre del producto no coincide");
		assertEquals(precioEsperado, p.getPrice(), "El precio del producto no coincide");
	}
	
	@Test
	@DisplayName("Test del constructor con nombre vacío")
	void testConstructorNombreVacío() {
		String mensajeEsperado = "Error: Nombre inválido";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product ("", 20));
		assertEquals(mensajeEsperado, exception.getMessage(), "El constructor permite un nombre vacío");
	}
	
	@Test
	@DisplayName("Test del constructor con nombre null")
	void testConstructorNombreNoValido() {
		String mensajeEsperado = "Error: Nombre inválido";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product (null, 20));
		assertEquals(mensajeEsperado, exception.getMessage(), "El constructor permite un nombre vacío");
	}
	
	@Test
	@DisplayName("Test del constructor con precio no válido")
	void testConstructorPrecioNoValido() {
		String mensajeEsperado = "Error: Precio negativo";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product ("Teclado", -10));
		assertEquals(mensajeEsperado, exception.getMessage(), "El constructor permite un precio negativo");
	}
	
	@Test
	@DisplayName("Test del método SetName con nombre válido")
	void testSetNameValido() {
		String resultadoEsperado = "Ratón";
		p.setName("Ratón");
		assertEquals(resultadoEsperado, p.getName(), "Los nombres no coinciden");
	}
	
	@Test
	@DisplayName("Test del método SetName con nombre vacío")
	void testSetNameVacio() {
		String mensajeEsperado = "Error: Nombre inválido";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> p.setName(""));
		assertEquals(mensajeEsperado, exception.getMessage(), "El método set permite un nombre vacío");
	}
	
	@Test
	@DisplayName("Test del método SetName con nombre null")
	void testSetNameNoValido() {
		String mensajeEsperado = "Error: Nombre inválido";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> p.setName(null));
		assertEquals(mensajeEsperado, exception.getMessage(), "El método set permite un nombre no válido");
	}
	
	@Test
	@DisplayName("Test del método SetName con precio válido")
	void testSetPriceValido() {
		double resultadoEsperado = 50;
		p.setPrice(50);
		assertEquals(resultadoEsperado, p.getPrice(), "Los precios no coinciden");
	}
	
	@Test
	@DisplayName("Test del método SetPrice con precio negativo")
	void testSetPriceNegativo() {
		String mensajeEsperado = "Error: Precio negativo";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> p.setPrice(-10));
		assertEquals(mensajeEsperado, exception.getMessage(), "El método set permite un precio negativo");
	}
	
	

}
