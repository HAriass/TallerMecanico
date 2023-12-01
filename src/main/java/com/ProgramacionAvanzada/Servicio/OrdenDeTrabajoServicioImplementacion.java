
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.OrdenDeTrabajoDAO;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenDeTrabajoServicioImplementacion implements OrdenDeTrabajoServicio{

    @Autowired
    private OrdenDeTrabajoDAO ordenDeTrabajoDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<OrdenDeTrabajo> listaOrdenDeTrabajo() {
        return (List<OrdenDeTrabajo>) ordenDeTrabajoDao.findAll();
    }

    @Override
    public void registrar(OrdenDeTrabajo ordenDeTrabajo) {
        ordenDeTrabajoDao.save(ordenDeTrabajo);
    }

    @Override
    public void eliminar(OrdenDeTrabajo ordenDeTrabajo) {
        ordenDeTrabajoDao.delete(ordenDeTrabajo);
    }

    @Override
    public OrdenDeTrabajo localizarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo) {
        return ordenDeTrabajoDao.findById(ordenDeTrabajo.getId()).orElse(null);
    }
    
    @Override
    public OrdenDeTrabajo obtenerOrdenDeTrabajoPorId(Long id) {
        // Obtener la orden de trabajo por su ID desde el repositorio
        Optional<OrdenDeTrabajo> ordenOptional = ordenDeTrabajoDao.findById(id);

        if (ordenOptional.isPresent()) {
            OrdenDeTrabajo ordenDeTrabajo = ordenOptional.get();

            // Agregar impresiones para verificar si se está obteniendo correctamente
            System.out.println("ID de la orden de trabajo: " + ordenDeTrabajo.getId());
            System.out.println("Vehículo: " + ordenDeTrabajo.getVehiculo()); // Verifica que esto no sea nulo
            System.out.println("Servicios: " + ordenDeTrabajo.getServicio()); // Verifica que esto no sea nulo
            // Agrega más impresiones para otros atributos relacionados si es necesario

            return ordenDeTrabajo;
        } else {
            System.out.println("No se encontró ninguna orden de trabajo con ID: " + id);
            return null; // Devolver null si no se encuentra la orden de trabajo
        }
    }

    @Override
    public List<OrdenDeTrabajo> obtenerOrdenesPorTecnicoYFechas(Long idTecnico, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener las órdenes de trabajo para un técnico y un rango de fechas
        return ordenDeTrabajoDao.findByTecnicoIdAndFechaCreacionBetween(idTecnico, fechaInicio, fechaFin);
    }

    @Override
    public List<OrdenDeTrabajo> obtenerOrdenesPorRangoDeFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return ordenDeTrabajoDao.findByFechaCreacionBetween(fechaInicio, fechaFin);
    }


    
}
