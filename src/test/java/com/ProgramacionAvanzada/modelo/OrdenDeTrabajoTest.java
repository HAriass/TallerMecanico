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
    @DisplayName("Crear OrdenDeTrabajo - Se verifica que se pueda crear una instancia de la clase OrdenDeTrabajo")
    public void testCrearOrdenDeTrabajo() {
        assertNotNull(ordenDeTrabajo);
        // Puedes agregar más aserciones aquí para verificar otros atributos
    }

    @Test
    @DisplayName("Calcular SubTotal - Se prueba el cálculo del subTotal")
    public void testCalcularSubTotal() {
        Servicio servicio1 = new Servicio();
        servicio1.setPrecio(100);
        Repuesto repuesto1 = new Repuesto();
        repuesto1.setPrecio(50);
        servicio1.getRepuestos().add(repuesto1);

        Servicio servicio2 = new Servicio();
        servicio2.setPrecio(75);

        List<Servicio> servicios = new ArrayList<>();
        servicios.add(servicio1);
        servicios.add(servicio2);

        ordenDeTrabajo.setServicio(servicios);

        assertEquals(225, ordenDeTrabajo.calcularSubTotal());
    }

    @Test
    @DisplayName("Calcular Total - Se prueba el cálculo del total")
    public void testCalcularTotal() {
        ordenDeTrabajo.setImpuesto(10);
        ordenDeTrabajo.setDescuento(5);

        Servicio servicio = new Servicio();
        servicio.setPrecio(100);

        List<Servicio> servicios = new ArrayList<>();
        servicios.add(servicio);

        ordenDeTrabajo.setServicio(servicios);

        ordenDeTrabajo.calcularTotal();

        assertEquals(104.5, ordenDeTrabajo.getTotal(), 6.0);
    }




    @Test
    @DisplayName("Calcular Diferencia de Fechas - Se prueba el cálculo de la diferencia de fechas")
    public void testCalcularDiferenciaFechas() {
        ordenDeTrabajo.setFechaCreacion(LocalDate.of(2023, 1, 1));
        ordenDeTrabajo.setFechaEntrega(LocalDate.of(2023, 1, 5));

        assertEquals(4, ordenDeTrabajo.calcularDiferenciaFechas());
        
        // Prueba con fechaEntrega nula
        ordenDeTrabajo.setFechaEntrega(null);
        assertEquals(0, ordenDeTrabajo.calcularDiferenciaFechas());
    }
   
}
