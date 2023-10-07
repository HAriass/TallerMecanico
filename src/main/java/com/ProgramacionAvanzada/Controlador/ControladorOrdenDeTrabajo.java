package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.ClienteServicio;
import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.modelo.Cliente;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Servicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.ArrayList;
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
    public String mostrarFormularioNuevaOrdenDeTrabajo(Model model) {
        List<Servicio> servicios = servicioServicio.listaServicios();
        List<Cliente> clientes = clienteServicio.listaClientes();
        model.addAttribute("servicios", servicios);
        model.addAttribute("clientes", clientes);
        model.addAttribute("ordenDeTrabajo", new OrdenDeTrabajo()); // Añadir una instancia de OrdenDeTrabajo para el formulario
        return "registrar-ordenDeTrabajo";
    }

    @PostMapping("/ordenDeTrabajo/registrada")
    public String registrarNuevaOrdenDeTrabajo(@Valid OrdenDeTrabajo ordenDeTrabajo, Errors error, Model model, HttpServletRequest request) {
        if (error.hasErrors()) {
            List<Servicio> servicios = servicioServicio.listaServicios();
            List<Cliente> clientes = clienteServicio.listaClientes();
            model.addAttribute("servicios", servicios);
            model.addAttribute("clientes", clientes);
            return "registrar-ordenDeTrabajo";
        }

        // Obtener los IDs de los servicios seleccionados desde el formulario
        String[] servicioIds = request.getParameterValues("servicios");

        // Asignar el cliente seleccionado a la orden de trabajo
        Long clienteId = Long.parseLong(request.getParameter("cliente"));
        Cliente cliente = clienteServicio.obtenerClientePorId(clienteId); // Debes implementar este método
        ordenDeTrabajo.setCliente(cliente);

        // Asignar los servicios seleccionados a la orden de trabajo
        List<Servicio> serviciosSeleccionados = new ArrayList<>();
        for (String servicioId : servicioIds) {
            Long id = Long.parseLong(servicioId);
            Servicio servicio = servicioServicio.obtenerServicioPorId(id); // Debes implementar este método
            serviciosSeleccionados.add(servicio);
        }
        ordenDeTrabajo.setServicio(serviciosSeleccionados); // Aquí asignamos la lista de servicios a través del setter

        ordenServicio.registrar(ordenDeTrabajo);
        System.out.println(ordenDeTrabajo);
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
