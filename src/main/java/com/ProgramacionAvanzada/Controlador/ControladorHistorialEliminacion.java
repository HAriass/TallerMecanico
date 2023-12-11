// Controlador para el historial de eliminación
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicioImplementacion;
import com.ProgramacionAvanzada.Servicio.ServicioServicioImplementacion;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControladorHistorialEliminacion {

    @Autowired
    private ServicioServicioImplementacion servicioServicioImplementacion;
    @Autowired
    private OrdenDeTrabajoServicioImplementacion ordenServicioImplementacion;

    @GetMapping("/historial-eliminacion")
    public String mostrarHistorialEliminacion(Model model) {
        model.addAttribute("serviciosEliminados", servicioServicioImplementacion.obtenerServiciosEliminados());
        return "historialEliminacion";
    }
    
    @GetMapping("/historial-eliminacion-ordenDeTrabajo")
    public String mostrarHistorialEliminacionOrden(Model model) {
        model.addAttribute("ordenesEliminadas", ordenServicioImplementacion.obtenerOrdenesEliminadas());
        return "historial-eliminacion-ordenDeTrabajo";
    }
    
    @GetMapping("/servicio/restaurar/{id}")
    public String restaurarServicio(@PathVariable Long id) {
    Servicio servicio = servicioServicioImplementacion.obtenerServicioPorId(id);
    if (servicio != null) {
        servicioServicioImplementacion.restaurar(servicio);
    }
    return "redirect:/historial-eliminacion";
    }
    
    @GetMapping("/ordenDeTrabajo/restaurar/{id}")
    public String restaurarOrdenDeTrabajo(@PathVariable Long id) {
    OrdenDeTrabajo orden = ordenServicioImplementacion.obtenerOrdenDeTrabajoPorId(id);
    if (orden != null) {
        ordenServicioImplementacion.restaurar(orden);
    }
    return "redirect:/historial-eliminacion-ordenDeTrabajo";
    }
}