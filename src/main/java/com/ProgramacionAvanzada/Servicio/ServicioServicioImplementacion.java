
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.ServicioDAO;
import com.ProgramacionAvanzada.modelo.Servicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServicioImplementacion implements ServicioServicio{

    @Autowired
    private ServicioDAO servicioDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Servicio> listaServicios() {
        return (List<Servicio>) servicioDao.findAll();
    }

    @Override
    public void registrar(Servicio servicio) {
        servicioDao.save(servicio);
    }

    @Override
    public void eliminar(Servicio servicio) {
        servicioDao.delete(servicio);
    }

    @Override
    public Servicio localizarServicio(Servicio servicio) {
        return servicioDao.findById(servicio.getId()).orElse(null);
    }

    @Override
    public Servicio obtenerServicioPorId(Long id) {
        return servicioDao.findById(id).orElse(null);
    }

    
}
