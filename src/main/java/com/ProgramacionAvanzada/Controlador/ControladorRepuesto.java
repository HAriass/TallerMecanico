
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.RepuestoServicio;
import com.ProgramacionAvanzada.modelo.Repuesto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorRepuesto {
    
    @Autowired
    private RepuestoServicio repuestoServicio;
    
 
    @GetMapping("/repuesto")
    public String listarRepuestos(@RequestParam(name = "nombre", required = false) String nombre, Model modelo) {
        List<Repuesto> repuestos;

        if (nombre != null && !nombre.isEmpty()) {
            // Si se proporciona un nombre, realiza la búsqueda por nombre
            repuestos = repuestoServicio.buscarPorNombre(nombre);
        } else {
            // Si no se proporciona un nombre, obtén todos los repuestos
            repuestos = repuestoServicio.listaRepuestos();
        }

        modelo.addAttribute("repuestos", repuestos);
        return "repuesto";
    }
  
    @GetMapping("/repuesto/nuevo")
    public String mostrarFormularioNuevaRepuesto(Repuesto repuesto, Model model) {
        model.addAttribute("repuesto", repuesto);
        return "registrar-repuesto";
    }
    
    @PostMapping("/repuesto/registrado")
    public String registrarNuevoRepuesto(@Valid Repuesto repuesto, Errors error, Model model, HttpServletRequest request) {
        if (error.hasErrors()){
            model.addAttribute("repuesto", repuesto);
            return "registrar-repuesto";
        }
        try {
            repuestoServicio.registrar(repuesto);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("repuesto", repuesto);
            model.addAttribute("error", "Ya existe un repuesto con el mismo nombre.");
            return "registrar-repuesto";
        }
        return "redirect:/repuesto";
    }
    @GetMapping("/repuesto/modificar/{id}")
    public String modificar(Repuesto repuesto, Model model) {
        repuesto = repuestoServicio.localizarRepuesto(repuesto);
        model.addAttribute("repuesto", repuesto);
        return "modificar-repuesto";
    }
    
    @PostMapping("/repuesto/modificar/{id}")
    public String modificarRepuesto(@Valid Repuesto repuesto, Errors error, Model model) {
        if(error.hasErrors()){
            model.addAttribute("repuesto", repuesto);
            return "modificar-repuesto";            
        }
        try {
           repuestoServicio.registrar(repuesto);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un repuesto con el mismo Id.");
            model.addAttribute("repuesto", repuesto);
            return "modificar-repuesto";   
        }
        
        return "redirect:/repuesto";
    }
    
    @GetMapping("/repuesto/eliminar/{id}")
    public String eliminar(Repuesto repuesto){
        
        try {
            repuestoServicio.eliminar(repuesto);
        } catch (DataIntegrityViolationException e) {
            return "excepcion-eliminar";
        }
        
        return "redirect:/repuesto";
    }
    @GetMapping("/repuesto/agregar-cantidad/{id}")
    public String mostrarFormularioAgregarCantidad(@PathVariable("id") Long repuestoId, Model model) {
        Repuesto repuestoSeleccionado = repuestoServicio.obtenerRepuestoPorId(repuestoId);
        model.addAttribute("repuestoSeleccionado", repuestoSeleccionado);
        return "repuesto-agregar";
    }

    @PostMapping("/repuesto/agregar-cantidad")
    public String agregarCantidadRepuesto(@ModelAttribute("repuesto") Repuesto repuesto, @RequestParam("repuestoId") Long repuestoId, @RequestParam("cantidad") int cantidad) {
        Repuesto repuestoSeleccionado = repuestoServicio.obtenerRepuestoPorId(repuestoId);

        if (repuestoSeleccionado != null) {
            int cantidadActual = repuestoSeleccionado.getCantidad();
            int nuevaCantidad = cantidadActual + cantidad;
            repuestoSeleccionado.setCantidad(nuevaCantidad);
            repuestoServicio.registrar(repuestoSeleccionado);
        }

        return "redirect:/repuesto";
    }
    
    @GetMapping("/repuesto/buscar-async")
    public String buscarRepuestosAsync(@RequestParam("nombre") String nombre, Model model) {
    List<Repuesto> repuestos = repuestoServicio.buscarPorNombre(nombre);
    model.addAttribute("repuestos", repuestos);
    return "repuesto :: #resultados";
    }


}
