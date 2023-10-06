package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.MarcaServicio;
import com.ProgramacionAvanzada.modelo.Marca;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
///
@Controller
public class ControladorMarca {

    @Autowired
    private MarcaServicio marcaServicio;

    @GetMapping("/marca")
    public String listarMarcas(Model modelo) {

        List<Marca> marcas = marcaServicio.listaMarcas();
        modelo.addAttribute("marcas", marcas);
        
        return "marca";
    }
    
    @GetMapping("/marca/nueva")
    public String mostrarFormularioNuevaMarca(Marca marca,Model model) {
        model.addAttribute("marca", marca);
        return "registrar-marca";
    }

    @PostMapping("/marca/registrada")
    public String registrarNuevaMarca(@Valid Marca marca, Errors error,Model model) {
        if (error.hasErrors()){
            model.addAttribute("marca", marca);
            return "registrar-marca";
        }
        try {
            marcaServicio.registrar(marca);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("marca", marca);
            model.addAttribute("error", "Ya existe una marca con el mismo nombre.");
            return "registrar-marca";
        }
        return "redirect:/marca";
    }

    
    @GetMapping("/modificar/{id}")
    public String modificar(Marca marca, Model modelo){
        marca = marcaServicio.localizarMarca(marca);
        modelo.addAttribute("marca",marca);
        return "modificar-marca";
    }

    @PostMapping("/marca/modificar/{id}")
    public String modificarMarca(@Valid Marca marca, Errors error,Model model) {
        if (error.hasErrors()){
            model.addAttribute("marca",marca);
            return "modificar-marca";
        }
        try {
            marcaServicio.registrar(marca);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("marca", marca);
            model.addAttribute("error", "Ya existe una marca con el mismo nombre.");
            return "modificar-marca"; // registrar
        }
        return "redirect:/marca";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(Marca marca){
        marcaServicio.eliminar(marca);
        return "redirect:/marca";
    }
    
}
