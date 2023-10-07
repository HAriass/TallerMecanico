
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.ClienteServicio;
import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.modelo.Cliente;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorOrdenDeTrabajo {

    @Autowired
    private OrdenDeTrabajoServicio ordenServicio;
    
    @Autowired
    private ServicioServicio servicioServicio;
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/ordenDeTrabajo")
    public String listarServicios(Model modelo) {
        List<OrdenDeTrabajo> ordenes = ordenServicio.listaOrdenDeTrabajo();
        modelo.addAttribute("ordenes", ordenes);
        return "ordenDeTrabajo";
    }

    @GetMapping("/ordenDeTrabajo/nueva")
    public String mostrarFormularioNuevaOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo, Model model, Cliente cliente, Servicio servicio) {
        List<Servicio> servicios = servicioServicio.listaServicios();
        List<Cliente> clientes = clienteServicio.listaClientes();
        model.addAttribute("ordenDeTrabajo", ordenDeTrabajo);
        model.addAttribute("servicios", servicios);
        model.addAttribute("clientes", clientes);
        return "registrar-ordenDeTrabajo";
    }

    @PostMapping("/ordenDeTrabajo/registrada")
    public String registrarNuevaOrdenDeTrabajo(@Valid OrdenDeTrabajo ordenDeTrabajo, Errors error, Model model) {
        if(error.hasErrors()){
            model.addAttribute("ordenDeTrabajo", ordenDeTrabajo);
            return "registrar-ordenDeTrabajo";            
        }
        
        ordenServicio.registrar(ordenDeTrabajo);
        return "redirect:/ordenDeTrabajo";
    }
    
    @GetMapping("/ordenDeTrabajo/modificar/{id}")
    public String modificar(OrdenDeTrabajo ordenDeTrabajo, Model modelo){
        ordenDeTrabajo = ordenServicio.localizarOrdenDeTrabajo(ordenDeTrabajo);
        modelo.addAttribute("ordenDeTrabajo",ordenDeTrabajo);
        return "modificar-ordenDeTrabajo";
    }   
    
    @PostMapping("/ordenDeTrabajo/modificar/{id}")
    public String modificarOrdenDeTrabajo(@Valid OrdenDeTrabajo ordenDeTrabajo, Errors error, Model model) {
        if(error.hasErrors()){
            model.addAttribute("ordenDeTrabajo", ordenDeTrabajo);
            return "modificar-ordenDeTrabajo";            
        }
        
        ordenServicio.registrar(ordenDeTrabajo);
        return "redirect:/ordenDeTrabajo";
    } 

    @GetMapping("/ordenDeTrabajo/eliminar/{id}")
    public String eliminar(OrdenDeTrabajo ordenDeTrabajo){
        ordenServicio.eliminar(ordenDeTrabajo);
        return "redirect:/ordenDeTrabajo";
    }
    
}
