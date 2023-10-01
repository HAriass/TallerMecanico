
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.MarcaServicio;
import com.ProgramacionAvanzada.Servicio.ModeloServicio;
import com.ProgramacionAvanzada.modelo.Marca;
import com.ProgramacionAvanzada.modelo.Modelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladoModelo {
    
    @Autowired
    private ModeloServicio modeloServicio;
    
    @Autowired
    private MarcaServicio marcaServicio;

    @GetMapping("/modelo")
    public String listarModelos(Model model){
        
        List<Modelo> modelos = modeloServicio.listaModelos();
        
        model.addAttribute("modelos", modelos);

        return "modelo";
    }
    
    @GetMapping("/modelo/nuevo")
    public String mostrarFormularioNuevaMarca(Model model) {
        List<Marca> marcas = marcaServicio.listaMarcas();
        model.addAttribute("marcas", marcas);
        return "registrar-modelo";
    }
    
    @PostMapping("/modelo/registrado")
    public String registrarNuevaMarca(Modelo modelo) {
        modeloServicio.registrar(modelo);
        return "redirect:/modelo";
    }
    
        
    @GetMapping("modelo/modificar/{id}")
    public String modificar(Modelo modelo, Model model){
        modelo = modeloServicio.localizarModelo(modelo);
        model.addAttribute("modelo",modelo);
        List<Marca> marcas = marcaServicio.listaMarcas();
        ///////// SOLO FALTA EL MODIFICAR DE ARREGLAR
        model.addAttribute("marcas", marcas);
        return "modificar-modelo";
    }
    
    @GetMapping("modelo/eliminar/{id}")
    public String eliminar(Modelo modelo){
        modeloServicio.eliminar(modelo);
        return "redirect:/modelo";
    }
}
