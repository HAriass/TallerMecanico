package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.TecnicoServicio;
import com.ProgramacionAvanzada.modelo.Tecnico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String mostrarFormularioNuevaTecnico() {
        return "registrar-tecnico";
    }

    @PostMapping("/tecnico/registrado")
    public String registrarNuevaTecnico(Tecnico tecnico) {
        tecnicoServicio.registrar(tecnico);
        return "redirect:/tecnico";
    }

    
    @GetMapping("/tecnico/modificar/{id}")
    public String modificar(Tecnico tecnico, Model modelo){
        tecnico = tecnicoServicio.localizarTecnico(tecnico);
        modelo.addAttribute("tecnico",tecnico);
        return "modificar-tecnico";
    }
    
    @GetMapping("/tecnico/eliminar/{id}")
    public String eliminar(Tecnico tecnico){
        tecnicoServicio.eliminar(tecnico);
        return "redirect:/tecnico";
    }
    
}
