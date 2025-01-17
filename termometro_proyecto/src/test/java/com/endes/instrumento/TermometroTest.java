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
	@DisplayName("Prueba válida de convertir a Farenheit") 
	void testValidoConvertirAFarenheit() {
		double resultadoEsperado = 96.8;
		assertEquals(resultadoEsperado, t.convertirAFahrenheit(), "La temperatura no coincide");
	}

}
