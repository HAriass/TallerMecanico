
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.OrdenDeTrabajoDAO;
import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
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
        // Implementa la lógica para obtener una orden de trabajo por su ID desde el repositorio
        // Puedes usar el método findById del repositorio
        return ordenDeTrabajoDao.findById(id).orElse(null);
    }
    
}
