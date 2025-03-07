package com.endes.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.endes.dao.ProductDAO;
import com.endes.entidad.Product;
import com.endes.exception.ProductNotFoundException;

class ProductManagerImplTest {
	
	private ProductManager productManager;
	private ProductDAO productDAOMock;

	@BeforeEach
	void setUp() throws Exception {
		productDAOMock = mock(ProductDAO.class);
		productManager = new ProductManagerImpl(productDAOMock);
	}

	@Test
	@DisplayName("Debe lanzar una excepción si el producto no existe")
	void shouldThrowExcepcionWhenProductNotFound() throws ProductNotFoundException {
		//Simular que el DAO lanza la excepcion cuando se busca "No exists"
		doThrow(new ProductNotFoundException("No se encontró el producto: "))
		.when(productDAOMock).findByName("NoExists");
		
		//verificar que findProduct efectivamente lanza la excepción
		assertThrows(ProductNotFoundException.class, ()->productManager.findProduct("NoExists"));
		
		//verificar que se llamo una sola vez
		verify(productDAOMock, times(1)).findByName("NoExists");
	}
	

	@Test
	@DisplayName("Debe encontrar un producto por su nombre")
	void shouldFindProductByName() throws ProductNotFoundException {
		Product mockProduct = new Product("Mouse", 20.13);
		
		when(productDAOMock.findByName("Mouse")).thenReturn(mockProduct);
		
		Product result = productManager.findProduct("Mouse");
	
		assertNotNull(result);
		assertEquals("Mouse", result.getName());
		//assertEquals(expected, result.getPrice());
		verify(productDAOMock, times(1)).findByName("Mouse");
	}

}
