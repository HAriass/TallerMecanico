package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.MarcaServicio;
import com.ProgramacionAvanzada.Servicio.ModeloServicio;
import com.ProgramacionAvanzada.Servicio.VehiculoServicio;
import com.ProgramacionAvanzada.modelo.Marca;
import com.ProgramacionAvanzada.modelo.Modelo;
import com.ProgramacionAvanzada.modelo.Vehiculo;
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
public class ControladorVehiculo {
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private MarcaServicio marcaServicio;
    
    @Autowired
    private ModeloServicio modeloServicio;
    
    
    @GetMapping("/vehiculo")
    public String listarVehiculos(Model modelo){
        List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();
        modelo.addAttribute("vehiculos", vehiculos);
        return "vehiculo";
    }
    
        
    @GetMapping("/vehiculo/nuevo")
    public String mostrarFormularioNuevaVehiculo(Model model, Vehiculo vehiculo) {
        List<Marca> marcas = marcaServicio.listaMarcas();
        List<Modelo> modelos = modeloServicio.listaModelos();
        model.addAttribute("marcas", marcas);
        model.addAttribute("modelos", modelos);
        model.addAttribute("vehiculo", vehiculo);
        return "registrar-vehiculo";
    }

    @PostMapping("/vehiculo/registrado")
    public String registrarNuevaVehiculo(@Valid Vehiculo vehiculo, Errors error, Model model) {

        if(error.hasErrors()){
            List<Marca> marcas = marcaServicio.listaMarcas();
            List<Modelo> modelos = modeloServicio.listaModelos();
            model.addAttribute("marcas", marcas);
            model.addAttribute("modelos", modelos);
            return "registrar-vehiculo";
        }
        try {
            vehiculoServicio.registrar(vehiculo);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Ya existe un vehiculo con la misma patente");
            List<Marca> marcas = marcaServicio.listaMarcas();
            List<Modelo> modelos = modeloServicio.listaModelos();
            model.addAttribute("marcas", marcas);
            model.addAttribute("modelos", modelos);
            return "registrar-vehiculo";
        }
        return "redirect:/vehiculo";
    }

    
    @GetMapping("/vehiculo/modificar/{id}")
    public String modificar(Vehiculo vehiculo, Model modelo){
        vehiculo = vehiculoServicio.localizarVehiculo(vehiculo);
        modelo.addAttribute("vehiculo",vehiculo);
        return "modificar-vehiculo";
    }
    
    @PostMapping("/vehiculo/modificar/{id}")
    public String modificarVehiculo(@Valid Vehiculo vehiculo, Errors error, Model model) {

        if(error.hasErrors()){
            List<Marca> marcas = marcaServicio.listaMarcas();
            List<Modelo> modelos = modeloServicio.listaModelos();
            model.addAttribute("marcas", marcas);
            model.addAttribute("modelos", modelos);
            return "modificar-vehiculo";
        }
        try {
            vehiculoServicio.registrar(vehiculo);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "Ya existe un vehiculo con la misma patente");
            List<Marca> marcas = marcaServicio.listaMarcas();
            List<Modelo> modelos = modeloServicio.listaModelos();
            model.addAttribute("marcas", marcas);
            model.addAttribute("modelos", modelos);
            return "modificar-vehiculo";
        }
        return "redirect:/vehiculo";
    }
    
    @GetMapping("/vehiculo/eliminar/{id}")
    public String eliminar(Vehiculo vehiculo){
        vehiculoServicio.eliminar(vehiculo);
        return "redirect:/vehiculo";
    }
}
