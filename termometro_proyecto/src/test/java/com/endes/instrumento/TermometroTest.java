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
	}
