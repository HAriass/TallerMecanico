

package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.ClienteServicio;
import com.ProgramacionAvanzada.modelo.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String mostrarFormularioNuevaCliente() {
        return "registrar-cliente";
    }

    @PostMapping("/cliente/registrado")
    public String registrarNuevaCliente(Cliente cliente) {
        clienteServicio.registrar(cliente);
        return "redirect:/cliente";
    }

    
    @GetMapping("/cliente/modificar/{id}")
    public String modificar(Cliente cliente, Model modelo){
        cliente = clienteServicio.localizarCliente(cliente);
        modelo.addAttribute("cliente",cliente);
        return "modificar-cliente";
    }
    
    @GetMapping("/cliente/eliminar/{id}")
    public String eliminar(Cliente cliente){
        clienteServicio.eliminar(cliente);
        return "redirect:/cliente";
    }
    
}
