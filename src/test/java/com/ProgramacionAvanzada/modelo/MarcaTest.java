package com.ProgramacionAvanzada.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MarcaTest {

    @Test
    @DisplayName("Crear Marca - Verificar que se pueda crear una instancia de la clase Marca")
    public void testCrearMarca() {
        System.out.println("=== Test: Crear Marca ===");
        Marca marca = new Marca();
        assertNotNull(marca, "La instancia de Marca no debería ser nula");
    }

    @Test
    @DisplayName("Set y Get de Nombre - Probar el funcionamiento de los métodos set y get")
    public void testSetAndGetNombre() {
        System.out.println("=== Test: Set y Get de Nombre ===");
        Marca marca = new Marca();
        String nombreEsperado = "Nike";
        marca.setNombre(nombreEsperado);
        assertEquals(nombreEsperado, marca.getNombre(), "El nombre debería ser igual al esperado");
    }

    @Test
    @DisplayName("Estado por Defecto es False - Probar el comportamiento por defecto del atributo estado de la clase Marca")
    public void testEstadoPorDefectoEsFalse() {
        System.out.println("=== Test: Estado por Defecto es False ===");
        Marca marca = new Marca();
        assertFalse(marca.isEstado(), "El estado por defecto debería ser falso");
    }

    @Test
    @DisplayName("Estado Puede Cambiar - Probar que el estado puede cambiar correctamente")
    public void testEstadoPuedeCambiar() {
        System.out.println("=== Test: Estado Puede Cambiar ===");
        Marca marca = new Marca();
        marca.setEstado(true);
        assertTrue(marca.isEstado(), "El estado debería cambiar a verdadero");
    }
}