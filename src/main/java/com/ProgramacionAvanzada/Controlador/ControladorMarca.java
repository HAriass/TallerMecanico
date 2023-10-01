package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.MarcaServicio;
import com.ProgramacionAvanzada.modelo.Marca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String mostrarFormularioNuevaMarca() {
        return "registrar-marca";
    }

    @PostMapping("/marca/registrada")
    public String registrarNuevaMarca(Marca marca) {
        marcaServicio.registrar(marca);
        return "redirect:/marca";
    }

    
    @GetMapping("/modificar/{id}")
    public String modificar(Marca marca, Model modelo){
        marca = marcaServicio.localizarMarca(marca);
        modelo.addAttribute("marca",marca);
        return "modificar-marca";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(Marca marca){
        marcaServicio.eliminar(marca);
        return "redirect:/marca";
    }
    
}
