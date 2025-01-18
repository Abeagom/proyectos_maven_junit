package com.endes.instrumento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TermometroTest {
	Termometro t = new Termometro(36);

	@Test
	@DisplayName("Prueba del método get temperatura") 
	void testGet() {
		double resultadoEsperado = 36;
		assertEquals(resultadoEsperado, t.getTemperaturaCelsius(), "La temperatura no coincide");
	}
	
	@Test
	@DisplayName("Prueba del método set temperatura") 
	void testSet() {
		double resultadoEsperado = 37;
		t.setTemperaturaCelsius(37);
		assertEquals(resultadoEsperado, t.getTemperaturaCelsius(), "La temperatura no coincide");
	}
	
	@Test
	@DisplayName("Prueba válida para convertir a Farenheit") 
	void testConvertirAFarenheit() {
		double resultadoEsperado = 96.8;
		assertEquals(resultadoEsperado, t.convertirAFahrenheit(), "La temperatura no coincide");
	}
	
	@Test
	@DisplayName("Prueba válida para convertir a Kelvin") 
	void testValidoConvertirAKelvin() {
		double resultadoEsperado = 309.15;
		assertEquals(resultadoEsperado, t.convertirAKelvin(), "La temperatura no coincide");
	}
	
	@Test
	@DisplayName("Prueba no válida para convertir a Kelvin") 
	void testNoValidoConvertirAKelvin() {
		Termometro t2 = new Termometro(-273.20); 
		String resultadoEsperado = "La temperatura no puede estar por debajo del cero absoluto.";
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
	    t2.convertirAKelvin();
	    });
	    assertEquals(resultadoEsperado, exception.getMessage(), "La temperatura no válida fue reconocida");
	    }
	
	@Test
	@DisplayName("Prueba válida para ajustar la temperatura") 
	void testValidoAjustarTemperatura() {
		double resultadoEsperado = 38;
		t.ajustarTemperatura(2);
	    assertEquals(resultadoEsperado, t.getTemperaturaCelsius(), "La temperatura no coincide");
	    }
	
	@Test
	@DisplayName("Prueba de temperatura en rango") 
	void testEstaEnRango() {
        assertTrue(t.estaEnRango(35, 37), "La temperatura dentro de rango no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de temperatura por debajo del rango") 
	void testPorDebajoDeRango() {
        assertFalse(t.estaEnRango(37, 38), "La temperatura dentro de rango no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de temperatura por encima del rango") 
	void testPorEncimaDeRango() {
        assertFalse(t.estaEnRango(34, 35), "La temperatura dentro de rango no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de EsCongelación por debajo de 0") 
	void testEsCongelacionTrue() {
		Termometro t2 = new Termometro(0);
        assertTrue(t2.esCongelacion(), "La temperatura por debajo de 0 no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de EsCongelación por encima de 0") 
	void testEsCongelaciónFalse() {
        assertFalse(t.esCongelacion(), "La temperatura por encima de 0 no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de EsEbullicion por encima de 100") 
	void testEsEBullicionTrue() {
		Termometro t2 = new Termometro(100);
        assertTrue(t2.esEbullicion(), "La temperatura por encima de 100 no es válida");
	    }
	
	@Test
	@DisplayName("Prueba de EsEbullicion por debajo de 100") 
	void testEsEbullicionFalse() {
        assertFalse(t.esEbullicion(), "La temperatura por debajo de 100 no es válida");
	    }
	}
