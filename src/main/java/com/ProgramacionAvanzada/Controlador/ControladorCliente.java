

package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.ClienteServicio;
import com.ProgramacionAvanzada.modelo.Cliente;
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
public class ControladorCliente {
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/cliente")
    public String listarClientes(Model modelo) {
        List<Cliente> clientes = clienteServicio.listaClientes();
        modelo.addAttribute("clientes", clientes);
        return "cliente";
    }
    
    @GetMapping("/cliente/nuevo")
    public String mostrarFormularioNuevaCliente(Cliente cliente, Model model) {
        model.addAttribute("cliente", cliente);
        return "registrar-cliente";
    }

    @PostMapping("/cliente/registrado")
    public String registrarNuevaCliente(@Valid Cliente cliente, Errors error, Model model) {
        if(error.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "registrar-cliente";         
        }
        try {
            clienteServicio.registrar(cliente);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un cliente con el mismo DNI.");
            model.addAttribute("cliente", cliente);
            return "registrar-cliente";   
        }
        
        return "redirect:/cliente";
    }

    
    @GetMapping("/cliente/modificar/{id}")
    public String modificar(Cliente cliente, Model modelo){
        cliente = clienteServicio.localizarCliente(cliente);
        modelo.addAttribute("cliente",cliente);
        return "modificar-cliente";
    }
    
    @PostMapping("/cliente/modificar/{id}")
    public String modificarCliente(@Valid Cliente cliente, Errors error, Model model) {
        if(error.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "modificar-cliente";            
        }
        try {
            clienteServicio.registrar(cliente);
        } catch (DataIntegrityViolationException ex) {
            // Maneja la excepción de violación de unicidad aquí
            model.addAttribute("error", "Ya existe un cliente con el mismo DNI.");
            model.addAttribute("cliente", cliente);
            return "modificar-cliente";   
        }
        
        return "redirect:/cliente";
    }
    
    @GetMapping("/cliente/eliminar/{id}")
    public String eliminar(Cliente cliente){
        
        try {
            clienteServicio.eliminar(cliente);
        } catch (DataIntegrityViolationException e) {
            return "excepcion-eliminar";
        }
        
        return "redirect:/cliente";
    }
    
}
