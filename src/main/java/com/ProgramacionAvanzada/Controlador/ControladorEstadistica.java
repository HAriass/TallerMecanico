package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.Servicio.TecnicoServicio;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import com.ProgramacionAvanzada.modelo.ServicioCantidad;
import com.ProgramacionAvanzada.modelo.Tecnico;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorEstadistica {

    @Autowired
    private OrdenDeTrabajoServicio ordenServicio;

    @Autowired
    private ServicioServicio servicioServicio;
    
    @Autowired
    private TecnicoServicio tecnicoServicio;
    
    @GetMapping("/tomarFechas")
    public String getFechas(){
        return "seleccionDeFechas";
    }
    

    @PostMapping("/estadistica/porcentajeServicios")
    public String mostrarPorcentajeServicios(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            Model modelo) {

        List<OrdenDeTrabajo> ordenes = ordenServicio.obtenerOrdenesPorRangoDeFecha(fechaInicio, fechaFin);
        List<Servicio> servicios = servicioServicio.listaServicios();

        List<ServicioCantidad> conteoServicios = inicializarConteoServicios(servicios);

        actualizarConteoServicios(ordenes, servicios, conteoServicios);

        // Imprime los datos en la consola del servidor para verificar
        System.out.println("Nombres de Servicios (Controlador): " + conteoServicios.stream().map(ServicioCantidad::getNombreServicio).collect(Collectors.toList()));
        System.out.println("Cantidades Utilizadas (Controlador): " + conteoServicios.stream().map(ServicioCantidad::getCantidadUtilizada).collect(Collectors.toList()));

        // Agrega las listas al modelo con los mismos nombres que en el HTML
        modelo.addAttribute("nombresServicios", conteoServicios.stream().map(ServicioCantidad::getNombreServicio).collect(Collectors.toList()));
        modelo.addAttribute("cantidadesUtilizadas", conteoServicios.stream().map(ServicioCantidad::getCantidadUtilizada).collect(Collectors.toList()));


        return "porcentajeServicios";
    }

    private List<ServicioCantidad> inicializarConteoServicios(List<Servicio> servicios) {
        List<ServicioCantidad> conteoServicios = new ArrayList<>(servicios.size());
        for (Servicio servicio : servicios) {
            conteoServicios.add(new ServicioCantidad(servicio.getNombre(), 0));
        }
        return conteoServicios;
    }

    private void actualizarConteoServicios(List<OrdenDeTrabajo> ordenes, List<Servicio> servicios, List<ServicioCantidad> conteoServicios) {
        for (OrdenDeTrabajo orden : ordenes) {
            List<Servicio> serviciosOrden = orden.getServicio();
            for (Servicio servicioOrden : serviciosOrden) {
                // Encuentra el índice del servicio en la lista
                int indiceServicio = servicios.indexOf(servicioOrden);
                if (indiceServicio != -1) {
                    ServicioCantidad servicioCantidad = conteoServicios.get(indiceServicio);
                    servicioCantidad.setCantidadUtilizada(servicioCantidad.getCantidadUtilizada() + 1);
                }
            }
        }
    }
    
    @GetMapping("/estadistica/cantidadOrdenesPorTecnico")
    public String mostrarFormulario(Model modelo) {
        return "estadistica-ordenPorTecnico";
    }
    @PostMapping("/estadisticaLaburoTecnico")
    public String mostrarDuracionOrdenesPorTecnico(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            Model modelo) {

        List<OrdenDeTrabajo> ordenes = ordenServicio.obtenerOrdenesPorRangoDeFecha(fechaInicio, fechaFin);

        // Mapa para almacenar las duraciones acumuladas por técnico
        Map<String, Long> duracionesPorTecnico = new HashMap<>();

        for (OrdenDeTrabajo orden : ordenes) {
            Tecnico tecnico = orden.getTecnico();
            long duracionOrden = orden.calcularDiferenciaFechas();

            System.out.println("Calculando duración para orden de " + tecnico.getNombre() + ": " + duracionOrden + " días");

            if (duracionOrden < 0) {
                System.out.println("¡Alerta! Duración negativa para la orden de " + tecnico.getNombre() + ". Fecha de inicio: " +
                        orden.getFechaCreacion() + ", Fecha de fin: " + orden.getFechaEntrega());
            }

            // Sumar la duración al valor existente o iniciar en 0 si no existe
            duracionesPorTecnico.merge(tecnico.getNombre(), duracionOrden, Long::sum);
        }

        // Convertir el mapa a listas para Thymeleaf
        List<String> nombresTecnicos = new ArrayList<>(duracionesPorTecnico.keySet());
        List<Long> duraciones = new ArrayList<>(duracionesPorTecnico.values());

        // Agrega las listas al modelo
        modelo.addAttribute("nombresTecnicos", nombresTecnicos);
        modelo.addAttribute("duraciones", duraciones);

        // Imprimir el resultado del cálculo
        System.out.println("Duraciones por Técnico: " + duracionesPorTecnico);

        // Redirige a la vistaResultados
        return "vistaResultados";
    }




    
    @GetMapping("/ordenDeTrabajo/filtroFecha")
    public String pedirFechas() {
        return "ordenDeTrabajo-seleccionFecha";
    }
    
    @PostMapping("/ordenDeTrabajo/filtroFechaTomada")
    public String tomarFecha(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin,
            Model modelo) {

        // Obtener la lista de órdenes para el técnico y el rango de fechas
        List<OrdenDeTrabajo> ordenes = ordenServicio.obtenerOrdenesPorRangoDeFecha(fechaInicio, fechaFin);
        
        System.out.println(ordenes);

        // Agregar los resultados al modelo para mostrarlos en la vista
        modelo.addAttribute("fechaInicio", fechaInicio);
        modelo.addAttribute("fechaFin", fechaFin);
        modelo.addAttribute("ordenes", ordenes);

        return "ordenesDeTrabajoFiltradas";  
    }


}
