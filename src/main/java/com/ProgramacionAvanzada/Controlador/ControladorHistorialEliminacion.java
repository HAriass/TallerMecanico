// Controlador para el historial de eliminación
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicioImplementacion;
import com.ProgramacionAvanzada.modelo.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorHistorialEliminacion {

    @Autowired
    private ServicioServicioImplementacion servicioServicioImplementacion;

    @GetMapping("/historial-eliminacion")
    public String mostrarHistorialEliminacion(Model model) {
        model.addAttribute("serviciosEliminados", servicioServicioImplementacion.obtenerServiciosEliminados());
        return "historialEliminacion";
    }
    
    @GetMapping("/servicio/restaurar/{id}")
    public String restaurarServicio(@PathVariable Long id) {
    Servicio servicio = servicioServicioImplementacion.obtenerServicioPorId(id);
    if (servicio != null) {
        servicioServicioImplementacion.restaurar(servicio);
    }
    return "redirect:/historial-eliminacion";
}
}