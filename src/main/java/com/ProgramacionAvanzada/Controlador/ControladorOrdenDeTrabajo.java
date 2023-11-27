package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.Servicio.OrdenDeTrabajoServicio;
import com.ProgramacionAvanzada.Servicio.ServicioServicio;
import com.ProgramacionAvanzada.Servicio.TecnicoServicio;
import com.ProgramacionAvanzada.Servicio.VehiculoServicio;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import com.ProgramacionAvanzada.modelo.Repuesto;
import com.ProgramacionAvanzada.modelo.Servicio;
import com.ProgramacionAvanzada.modelo.Tecnico;
import com.ProgramacionAvanzada.modelo.Vehiculo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorOrdenDeTrabajo {

    @Autowired
    private OrdenDeTrabajoServicio ordenServicio;
    
    @Autowired
    private ServicioServicio servicioServicio;
    
    @Autowired
    private VehiculoServicio vehiculoServicio;
    
    @Autowired
    private TecnicoServicio tecnicoServicio;
    
    @GetMapping("/ordenDeTrabajo")
    public String listarServicios(Model modelo) {
        List<OrdenDeTrabajo> ordenes = ordenServicio.listaOrdenDeTrabajo();
        List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();
        modelo.addAttribute("ordenes", ordenes);
        modelo.addAttribute("vehiculos", vehiculos);
        return "ordenDeTrabajo";
    }

    @GetMapping("/ordenDeTrabajo/nueva")
    public String mostrarFormularioNuevaOrdenDeTrabajo(Model model) {
        List<Tecnico> tecnicos = tecnicoServicio.listaTecnicos();
        List<Servicio> servicios = servicioServicio.listaServicios();
        List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();
        model.addAttribute("servicios", servicios);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("tecnicos", tecnicos);
        model.addAttribute("ordenDeTrabajo", new OrdenDeTrabajo());
        return "registrar-ordenDeTrabajo";
    }

    @PostMapping("/ordenDeTrabajo/registrada")
    public String registrarNuevaOrdenDeTrabajo(@Valid OrdenDeTrabajo ordenDeTrabajo, Errors error, Model model, HttpServletRequest request) {
        if (error.hasErrors()) {
            List<Tecnico> tecnicos = tecnicoServicio.listaTecnicos();
            List<Servicio> servicios = servicioServicio.listaServicios();
            List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();
            model.addAttribute("servicios", servicios);
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("tecnicos", tecnicos);
            return "registrar-ordenDeTrabajo";
        }

         // Obtener el descuento desde el formulario
        int descuento = Integer.parseInt(request.getParameter("descuento"));
        ordenDeTrabajo.setDescuento(descuento);

        // Obtener los IDs de los servicios seleccionados desde el formulario
        String[] servicioIds = request.getParameterValues("servicios");

        // Asignar los servicios seleccionados a la orden de trabajo
        List<Servicio> serviciosSeleccionados = new ArrayList<>();
        for (String servicioId : servicioIds) {
            Long id = Long.parseLong(servicioId);
            Servicio servicio = servicioServicio.obtenerServicioPorId(id); // Debes implementar este método
            serviciosSeleccionados.add(servicio);
        }
        ordenDeTrabajo.setServicio(serviciosSeleccionados); // Aquí asignamos la lista de servicios a través del setter

        // Obtener el ID del técnico seleccionado desde el formulario
        String tecnicoId = request.getParameter("tecnico");
        if (tecnicoId != null && !tecnicoId.isEmpty()) {
            Long id = Long.parseLong(tecnicoId);
            Tecnico tecnico = tecnicoServicio.obtenerTecnicoPorId(id); // Debes implementar este método
            ordenDeTrabajo.setTecnico(tecnico); // Asignar el técnico seleccionado a la orden de trabajo
        }

        // Calcular el total después de establecer el descuento
        ordenDeTrabajo.calcularTotal();
        // Descontar la cantidad de repuestos correspondiente a cada servicio
        for (Servicio servicio : ordenDeTrabajo.getServicio()) {
            for (Repuesto repuesto : servicio.getRepuestos()) {
                int cantidadActual = repuesto.getCantidad();
                int cantidadDescontar = 1; // Puedes ajustar la cantidad a descontar según tus necesidades
                repuesto.setCantidad(cantidadActual - cantidadDescontar);
            }
        }

        ordenServicio.registrar(ordenDeTrabajo);
        System.out.println("Objeto orde:"+ordenDeTrabajo);
        return "redirect:/ordenDeTrabajo";
    }

    @GetMapping("/ordenDeTrabajo/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        OrdenDeTrabajo ordenDeTrabajo = ordenServicio.obtenerOrdenDeTrabajoPorId(id);
        List<Servicio> servicios = servicioServicio.listaServicios();
        List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();

        // Establecer el vehículo seleccionado en la orden de trabajo
        Vehiculo vehiculoSeleccionado = ordenDeTrabajo.getVehiculo();
        model.addAttribute("vehiculoSeleccionado", vehiculoSeleccionado);

        // Establecer los servicios seleccionados en la orden de trabajo
        List<Servicio> serviciosSeleccionados = ordenDeTrabajo.getServicio();
        model.addAttribute("serviciosSeleccionados", serviciosSeleccionados);

        // Establecer la lista de técnicos en el modelo
        List<Tecnico> tecnicos = tecnicoServicio.listaTecnicos();
        model.addAttribute("tecnicos", tecnicos);

        // Establecer el técnico seleccionado en la orden de trabajo
        Tecnico tecnicoSeleccionado = ordenDeTrabajo.getTecnico();
        String informacionRelevante = ordenDeTrabajo.getInformacionRelevante();
        System.out.println("Información Relevante: " + informacionRelevante);
        model.addAttribute("informacionRelevante", informacionRelevante);

        model.addAttribute("servicios", servicios);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("ordenDeTrabajo", ordenDeTrabajo);
        model.addAttribute("tecnicoSeleccionado", tecnicoSeleccionado);

        return "modificar-ordenDeTrabajo";
    }

    @PostMapping("/ordenDeTrabajo/modificar/{id}")
    public String modificarOrdenDeTrabajo(@PathVariable Long id, @Valid OrdenDeTrabajo ordenDeTrabajo, Errors error, Model model, HttpServletRequest request) {
        if (error.hasErrors()) {
            List<Servicio> servicios = servicioServicio.listaServicios();
            List<Vehiculo> vehiculos = vehiculoServicio.listaVehiculos();
            model.addAttribute("servicios", servicios);
            model.addAttribute("vehiculos", vehiculos);
            return "modificar-ordenDeTrabajo";
        }

        // Obtener el ID del técnico seleccionado desde el formulario
        String tecnicoId = request.getParameter("tecnico");
        if (tecnicoId != null && !tecnicoId.isEmpty()) {
            Long tecnicoIdLong = Long.parseLong(tecnicoId);
            Tecnico tecnico = tecnicoServicio.obtenerTecnicoPorId(tecnicoIdLong); // Debes implementar este método
            ordenDeTrabajo.setTecnico(tecnico); // Asignar el técnico seleccionado a la orden de trabajo
        }
         // Obtener el descuento desde el formulario
        int descuento = Integer.parseInt(request.getParameter("descuento"));

        // Obtener los IDs de los servicios seleccionados desde el formulario
        String[] servicioIds = request.getParameterValues("servicios");

        // Verificar si se seleccionaron servicios
        List<Servicio> serviciosSeleccionados = new ArrayList<>();
        if (servicioIds != null) {
            for (String servicioId : servicioIds) {
                Long servicioIdLong = Long.parseLong(servicioId);
                Servicio servicio = servicioServicio.obtenerServicioPorId(servicioIdLong); // Debes implementar este método
                serviciosSeleccionados.add(servicio);
            }
        }

        ordenDeTrabajo.setServicio(serviciosSeleccionados); // Aquí asignamos la lista de servicios a través del setter

        // Modificar la orden de trabajo existente (puedes usar el ID para identificarla)
        OrdenDeTrabajo ordenExistente = ordenServicio.obtenerOrdenDeTrabajoPorId(id); 
        if (ordenExistente != null) {
            // Actualizar los datos de la orden de trabajo existente
            ordenExistente.setVehiculo(ordenDeTrabajo.getVehiculo());
            ordenExistente.setServicio(ordenDeTrabajo.getServicio());
            ordenExistente.setFechaCreacion(ordenDeTrabajo.getFechaCreacion());
            ordenExistente.setInformacionRelevante(ordenDeTrabajo.getInformacionRelevante());
            ordenExistente.setTecnico(ordenDeTrabajo.getTecnico()); // Actualizar también el técnico
            ordenExistente.setDescuento(descuento);
            ordenExistente.calcularTotal();
            ordenServicio.registrar(ordenExistente); // Actualizar la orden existente en la base de datos
        }

        return "redirect:/ordenDeTrabajo";
    }



    @GetMapping("/ordenDeTrabajo/eliminar/{id}")
    public String eliminar(OrdenDeTrabajo ordenDeTrabajo){
        ordenServicio.eliminar(ordenDeTrabajo);
        return "redirect:/ordenDeTrabajo";
    }
    
    
}
