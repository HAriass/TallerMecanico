package com.ProgramacionAvanzada.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrdenDeTrabajoTest {

    private OrdenDeTrabajo ordenDeTrabajo;

    @BeforeEach
    void setUp() {
        ordenDeTrabajo = new OrdenDeTrabajo();
    }

    @Test
    @DisplayName("Crear OrdenDeTrabajo - Verificar que se pueda crear una instancia de la clase OrdenDeTrabajo")
    public void testCrearOrdenDeTrabajo() {
        System.out.println("=== Test: Orden De Trabajo ===");
        assertNotNull(ordenDeTrabajo, "La instancia de OrdenDeTrabajo no debería ser nula");
        // Puedes agregar más aserciones aquí para verificar otros atributos
    }

    @Test
    @DisplayName("Calcular SubTotal - Probar el cálculo del subTotal")
    public void testCalcularSubTotal() {
        System.out.println("=== Test: Calcular SubTotal ===");
        Servicio servicio1 = new Servicio();
        servicio1.setPrecio(100);
        Repuesto repuesto1 = new Repuesto();
        repuesto1.setPrecio(50);
        servicio1.getRepuestos().add(repuesto1);

        Servicio servicio2 = new Servicio();
        servicio2.setPrecio(75);

        List<Servicio> servicios = List.of(servicio1, servicio2);

        ordenDeTrabajo.setServicio(servicios);

        assertEquals(225, ordenDeTrabajo.calcularSubTotal(), "El cálculo del subTotal no es correcto");
    }

    @Test
    @DisplayName("Calcular Total - Probar el cálculo del total")
    public void testCalcularTotal() {
        System.out.println("=== Test: Calcular Total ===");
        ordenDeTrabajo.setImpuesto(10);
        ordenDeTrabajo.setDescuento(5);

        Servicio servicio = new Servicio();
        servicio.setPrecio(100);

        List<Servicio> servicios = List.of(servicio);

        ordenDeTrabajo.setServicio(servicios);

        ordenDeTrabajo.calcularTotal();

        assertEquals(104.5, ordenDeTrabajo.getTotal(), 0.001, "El cálculo del total no es correcto");
    }

    @Test
    @DisplayName("Calcular Diferencia de Fechas - Probar el cálculo de la diferencia de fechas")
    public void testCalcularDiferenciaFechas() {
        System.out.println("=== Test: Calcular Diferencia De Fechas ===");
        ordenDeTrabajo.setFechaCreacion(LocalDate.of(2023, 1, 1));
        ordenDeTrabajo.setFechaEntrega(LocalDate.of(2023, 1, 5));

        assertEquals(4, ordenDeTrabajo.calcularDiferenciaFechas(), "La diferencia de fechas no es la esperada");

        // Prueba con fechaEntrega nula
        ordenDeTrabajo.setFechaEntrega(null);
        assertEquals(0, ordenDeTrabajo.calcularDiferenciaFechas(), "La diferencia de fechas debería ser cero con fechaEntrega nula");
    }
}
