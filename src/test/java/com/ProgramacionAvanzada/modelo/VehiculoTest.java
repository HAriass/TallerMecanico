package com.ProgramacionAvanzada.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para la clase Vehiculo")
public class VehiculoTest {

    private Vehiculo vehiculo;

    @BeforeEach
    public void setUp() {
        vehiculo = new Vehiculo();
    }

    @Test
    @DisplayName("Crear Vehiculo - Se verifica que se pueda crear una instancia de la clase Vehiculo")
    public void testCrearVehiculo() {
        System.out.println("=== Test: Crear Vehiculo ===");
        assertNotNull(vehiculo);
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Marca - Se prueba el funcionamiento de los métodos set y get para la marca")
    public void testSetAndGetMarca() {
        System.out.println("=== Test: Asignar y Obtener Marca ===");
        Marca marca = new Marca();
        vehiculo.setMarca(marca);
        assertEquals(marca, vehiculo.getMarca());
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Modelo - Se prueba el funcionamiento de los métodos set y get para el modelo")
    public void testSetAndGetModelo() {
        System.out.println("=== Test: Asignar y Obtener Modelo ===");
        Modelo modelo = new Modelo();
        vehiculo.setModelo(modelo);
        assertEquals(modelo, vehiculo.getModelo());
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Patente - Se prueba el funcionamiento de los métodos set y get para la patente")
    public void testSetAndGetPatente() {
        System.out.println("=== Test: Asignar y Obtener Patente ===");
        String patente = "ABC123";
        vehiculo.setPatente(patente);
        assertEquals(patente, vehiculo.getPatente());
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Cliente - Se prueba el funcionamiento de los métodos set y get para el cliente")
    public void testSetAndGetCliente() {
        System.out.println("=== Test: Asignar y Obtener Cliente ===");
        Cliente cliente = new Cliente();
        vehiculo.setCliente(cliente);
        assertEquals(cliente, vehiculo.getCliente());
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Estado por Defecto es False - Se prueba el comportamiento por defecto del atributo estado de la clase Vehiculo")
    public void testEstadoPorDefectoEsFalse() {
        System.out.println("=== Test: Estado por Defecto es False ===");
        assertFalse(vehiculo.isEstado());
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Estado - Se prueba el funcionamiento de los métodos set y get para el estado")
    public void testSetAndGetEstado() {
        System.out.println("=== Test: Asignar y Obtener Estado ===");
        vehiculo.setEstado(true);
        assertTrue(vehiculo.isEstado());
        System.out.println("Resultado: Exitoso");
    }
}