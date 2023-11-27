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
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    

    @GetMapping("/estadistica/porcentajeServicios")
    public String mostrarPorcentajeServicios(Model modelo) {

         List<OrdenDeTrabajo> ordenes = ordenServicio.listaOrdenDeTrabajo();
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
        // Obtén la lista de técnicos (asumo que tienes un servicio para obtenerlos)
        List<Tecnico> tecnicos = tecnicoServicio.listaTecnicos();

        modelo.addAttribute("tecnicos", tecnicos);

        return "estadistica-ordenPorTecnico";
    }
    @PostMapping("/estadistica/cantidadOrdenesPorTecnico")
    public String procesarFormulario(
            @RequestParam("tecnico") Long idTecnico,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            Model modelo) {

        // Obtener la lista de órdenes para el técnico y el rango de fechas
        List<OrdenDeTrabajo> ordenes = ordenServicio.obtenerOrdenesPorTecnicoYFechas(idTecnico, fechaInicio, fechaFin);

        // Contar la cantidad de órdenes
        int cantidadOrdenes = ordenes.size();

        // Agregar los resultados al modelo para mostrarlos en la vista
        modelo.addAttribute("tecnico", ordenes.isEmpty() ? null : ordenes.get(0).getTecnico()); // Asumiendo que la lista no estará vacía
        modelo.addAttribute("fechaInicio", fechaInicio);
        modelo.addAttribute("fechaFin", fechaFin);
        modelo.addAttribute("cantidadOrdenes", cantidadOrdenes);

        return "vistaResultados";  // Reemplaza "vistaResultados" con el nombre de tu vista.
    }


}
