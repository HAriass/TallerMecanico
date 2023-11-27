package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import com.ProgramacionAvanzada.modelo.ServicioCantidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorEstadistica {

    @Autowired
    private OrdenDeTrabajoServicio ordenServicio;

    @Autowired
    private ServicioServicio servicioServicio;

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
}
