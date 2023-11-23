package com.ProgramacionAvanzada.Controlador;


import com.ProgramacionAvanzada.Servicio.RepuestoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.modelo.Repuesto;
import com.ProgramacionAvanzada.modelo.Servicio;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorServicio {

    @Autowired
    private ServicioServicio servicioServicio;
    
    @Autowired
    private RepuestoServicio repuestoServicio;


    @GetMapping("/servicio")
    public String listarServicios(Model modelo) {
        List<Servicio> servicios = servicioServicio.listaServicios();
        modelo.addAttribute("servicios", servicios);
        return "servicio";
    }

@GetMapping("/servicio/nuevo")
public String mostrarFormularioNuevoServicio(Servicio servicio, Model model, Repuesto repuesto) {
    model.addAttribute("servicio", servicio);
    List<Repuesto> repuestos = repuestoServicio.listaRepuestos();
    model.addAttribute("repuestos", repuestos);
    return "registrar-servicio";
}


    @PostMapping("/servicio/registrado")
    public String registrarNuevoServicio(@Valid Servicio servicio, Errors error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("servicio", servicio);
            return "registrar-servicio";
        }
        try {
            servicioServicio.registrar(servicio);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un servicio con el mismo nombre");
            model.addAttribute("servicio", servicio);
            return "registrar-servicio";
        }

        return "redirect:/servicio";
    }

    @GetMapping("/servicio/modificar/{id}")
    public String modificar(Servicio servicio, Model modelo) {
        servicio = servicioServicio.localizarServicio(servicio);
        modelo.addAttribute("servicio", servicio);
        return "modificar-servicio";
    }

    @PostMapping("/servicio/modificar/{id}")
    public String modificarCliente(@Valid Servicio servicio, Errors error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("servicio", servicio);
            return "modificar-servicio";
        }
        try {
            servicioServicio.registrar(servicio);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un servicio con el mismo nombre");
            model.addAttribute("servicio", servicio);
            return "modificar-servicio";
        }

        return "redirect:/servicio";
    }

    @GetMapping("/servicio/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        Servicio servicio = servicioServicio.obtenerServicioPorId(id);

        if (servicio != null) {
            servicioServicio.eliminar(servicio);
        }

        return "redirect:/servicio";
    }
}
