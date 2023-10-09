
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.MarcaServicio;
import com.ProgramacionAvanzada.Servicio.ModeloServicio;
import com.ProgramacionAvanzada.modelo.Marca;
import com.ProgramacionAvanzada.modelo.Modelo;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String mostrarFormularioNuevaMarca(Model model,Modelo modelo) {
        List<Marca> marcas = marcaServicio.listaMarcas();
        model.addAttribute("marcas", marcas);
        model.addAttribute("modelo", modelo);
        return "registrar-modelo";
    }
    
    @PostMapping("/modelo/registrado")
    public String registrarNuevoModelo(@Valid Modelo modelo, Errors error, Model model) {
        if (error.hasErrors()) {
            List<Marca> marcas = marcaServicio.listaMarcas();
            model.addAttribute("marcas", marcas);
            return "registrar-modelo";
        }

        int anio;
        try {
            anio = Integer.parseInt(modelo.getAnio());
            if (anio < 1917 || anio > 2023) {
                model.addAttribute("errorAnio", "El año es inválido. Debe estar entre 1917 y 2023.");
                List<Marca> marcas = marcaServicio.listaMarcas();
                model.addAttribute("marcas", marcas);
                return "registrar-modelo";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("errorAnio", "El año debe ser un número válido.");
            List<Marca> marcas = marcaServicio.listaMarcas();
            model.addAttribute("marcas", marcas);
            return "registrar-modelo";
        }

        try {
            modeloServicio.registrar(modelo);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe el modelo con ese nombre.");
                List<Marca> marcas = marcaServicio.listaMarcas();
                model.addAttribute("marcas", marcas);
                return "registrar-modelo";
        }

        // Si todo está correcto, puedes redirigir si lo deseas
        return "redirect:/modelo";
    }

    
        
    @GetMapping("modelo/modificar/{id}")
    public String modificar(Modelo modelo, Model model){
        modelo = modeloServicio.localizarModelo(modelo);
        model.addAttribute("modelo",modelo);
        List<Marca> marcas = marcaServicio.listaMarcas();
        model.addAttribute("marcas", marcas);
        return "modificar-modelo";
    }
    
    @PostMapping("/modelo/modificar/{id}")
        public String modificarModelo(@Valid Modelo modelo, Errors error, Model model) {
            if (error.hasErrors()) {
                List<Marca> marcas = marcaServicio.listaMarcas();
                model.addAttribute("marcas", marcas);
                return "registrar-modelo";
            }

            int anio;
            try {
                anio = Integer.parseInt(modelo.getAnio());
                if (anio < 1917 || anio > 2023) {
                    model.addAttribute("errorAnio", "El año es inválido. Debe estar entre 1917 y 2023.");
                    List<Marca> marcas = marcaServicio.listaMarcas();
                    model.addAttribute("marcas", marcas);
                    return "registrar-modelo";
                }
            } catch (NumberFormatException e) {
                model.addAttribute("errorAnio", "El año debe ser un número válido.");
                List<Marca> marcas = marcaServicio.listaMarcas();
                model.addAttribute("marcas", marcas);
                return "registrar-modelo";
            }

            try {
                modeloServicio.registrar(modelo);
            } catch (DataIntegrityViolationException ex) {
                // Maneja la excepción de violación de unicidad aquí
                model.addAttribute("error", "Ya existe el modelo con ese nombre.");
                    List<Marca> marcas = marcaServicio.listaMarcas();
                    model.addAttribute("marcas", marcas);
                    return "registrar-modelo";
            }

            // Si todo está correcto, puedes redirigir si lo deseas
            return "redirect:/modelo";
        }
    
    @GetMapping("modelo/eliminar/{id}")
    public String eliminar(Modelo modelo){
        try {
            modeloServicio.eliminar(modelo);
        } catch (DataIntegrityViolationException e) {
            return "excepcion-eliminar";
        }
        
        return "redirect:/modelo";
    }
    
    @GetMapping("/obtenerModelosPorMarca/{marcaId}")
    @ResponseBody
    public ResponseEntity<List<Modelo>> obtenerModelosPorMarca(@PathVariable Long marcaId) {
        try {
            List<Modelo> modelos = modeloServicio.obtenerModelosPorMarca(marcaId); // Implementa este método en tu servicio
            return ResponseEntity.ok(modelos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
