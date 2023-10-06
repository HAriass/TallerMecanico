package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.TecnicoServicio;
import com.ProgramacionAvanzada.modelo.Cliente;
import com.ProgramacionAvanzada.modelo.Tecnico;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorTecnico {
    @Autowired
    private TecnicoServicio tecnicoServicio;

    @GetMapping("/tecnico")
    public String listarTecnicos(Model modelo) {
        List<Tecnico> tecnicos = tecnicoServicio.listaTecnicos();
        modelo.addAttribute("tecnicos", tecnicos);
        return "tecnico";
    }
    
    @GetMapping("/tecnico/nuevo")
    public String mostrarFormularioNuevaTecnico(Tecnico tecnico, Model model) {
        model.addAttribute("tecnico", tecnico);
        return "registrar-tecnico";
    }

    @PostMapping("/tecnico/registrado")
    public String registrarNuevaTecnico(@Valid Tecnico tecnico, Errors error, Model model) {
        if (error.hasErrors()){
            model.addAttribute("tecnico", tecnico);
            return "registrar-tecnico";
        }
        try {
            tecnicoServicio.registrar(tecnico);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un tecnico con el mismo DNI.");
            model.addAttribute("tecnico", tecnico);
            return "registrar-tecnico";
        }
        
        return "redirect:/tecnico";
    }

    
    @GetMapping("/tecnico/modificar/{id}")
    public String modificar(Tecnico tecnico, Model modelo){
        tecnico = tecnicoServicio.localizarTecnico(tecnico);
        modelo.addAttribute("tecnico",tecnico);
        return "modificar-tecnico";
    }
    
    @PostMapping("/tecnico/modificar/{id}")
    public String modificarTecnico(@Valid Tecnico tecnico, Errors error, Model model) {
        if (error.hasErrors()){
            model.addAttribute("tecnico", tecnico);
            return "modificar-tecnico";
        }
        try {
            tecnicoServicio.registrar(tecnico);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un tecnico con el mismo DNI.");
            model.addAttribute("tecnico", tecnico);
            return "modificar-tecnico";
        }
        
        return "redirect:/tecnico";
    }
    
    @GetMapping("/tecnico/eliminar/{id}")
    public String eliminar(Tecnico tecnico){
        tecnicoServicio.eliminar(tecnico);
        return "redirect:/tecnico";
    }
    
}
