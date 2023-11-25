package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    List<Integer> conteoServicios = new ArrayList<>(servicios.size());

    // Inicializa la lista de conteo de servicios con ceros
    for (int i = 0; i < servicios.size(); i++) {
        conteoServicios.add(0);
    }

    // Itera sobre las órdenes de trabajo y actualiza el conteo de servicios
    for (OrdenDeTrabajo orden : ordenes) {
        List<Servicio> serviciosOrden = orden.getServicio();

        for (Servicio servicioOrden : serviciosOrden) {
            // Encuentra el índice del servicio en la lista
            int indiceServicio = servicios.indexOf(servicioOrden);

            if (indiceServicio != -1) { // Asegura que el servicio esté en la lista
                int cantidadActual = conteoServicios.get(indiceServicio);
                conteoServicios.set(indiceServicio, cantidadActual + 1);
            }
        }
    }
    

    // Imprime el contenido de las variables en la consola del servidor
    System.out.println("Conteo de Servicios: " + conteoServicios);
    System.out.println("Servicios: " + servicios);

    // Ahora, la lista conteoServicios contiene el número de veces que cada servicio fue utilizado
    modelo.addAttribute("conteoServicios", conteoServicios);
    modelo.addAttribute("servicios", servicios);

    return "porcentajeServicios";
}

}
