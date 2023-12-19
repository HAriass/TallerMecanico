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
    @DisplayName("Crear Vehiculo - Verificar que se pueda crear una instancia de la clase Vehiculo")
    public void testCrearVehiculo() {
        System.out.println("=== Test: Crear Vehiculo ===");
        assertNotNull(vehiculo, "La instancia de Vehiculo no debería ser nula");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Marca - Probar el funcionamiento de los métodos set y get para la marca")
    public void testSetAndGetMarca() {
        System.out.println("=== Test: Asignar y Obtener Marca ===");
        Marca marca = new Marca();
        vehiculo.setMarca(marca);
        assertEquals(marca, vehiculo.getMarca(), "La marca asignada no es igual a la recuperada");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Modelo - Probar el funcionamiento de los métodos set y get para el modelo")
    public void testSetAndGetModelo() {
        System.out.println("=== Test: Asignar y Obtener Modelo ===");
        Modelo modelo = new Modelo();
        vehiculo.setModelo(modelo);
        assertEquals(modelo, vehiculo.getModelo(), "El modelo asignado no es igual al recuperado");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Patente - Probar el funcionamiento de los métodos set y get para la patente")
    public void testSetAndGetPatente() {
        System.out.println("=== Test: Asignar y Obtener Patente ===");
        String patente = "ABC123";
        vehiculo.setPatente(patente);
        assertEquals(patente, vehiculo.getPatente(), "La patente asignada no es igual a la recuperada");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Cliente - Probar el funcionamiento de los métodos set y get para el cliente")
    public void testSetAndGetCliente() {
        System.out.println("=== Test: Asignar y Obtener Cliente ===");
        Cliente cliente = new Cliente();
        vehiculo.setCliente(cliente);
        assertEquals(cliente, vehiculo.getCliente(), "El cliente asignado no es igual al recuperado");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Estado por Defecto es False - Probar el comportamiento por defecto del atributo estado de la clase Vehiculo")
    public void testEstadoPorDefectoEsFalse() {
        System.out.println("=== Test: Estado por Defecto es False ===");
        assertFalse(vehiculo.isEstado(), "El estado por defecto debería ser falso");
        System.out.println("Resultado: Exitoso");
    }

    @Test
    @DisplayName("Asignar y Obtener Estado - Probar el funcionamiento de los métodos set y get para el estado")
    public void testSetAndGetEstado() {
        System.out.println("=== Test: Asignar y Obtener Estado ===");
        vehiculo.setEstado(true);
        assertTrue(vehiculo.isEstado(), "El estado asignado no es igual al recuperado");
        System.out.println("Resultado: Exitoso");
    }
}