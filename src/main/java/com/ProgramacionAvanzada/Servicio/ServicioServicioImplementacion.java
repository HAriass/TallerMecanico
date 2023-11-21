
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.ServicioDAO;
import com.ProgramacionAvanzada.modelo.Servicio;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
public class ServicioServicioImplementacion implements ServicioServicio {

    @Autowired
    private ServicioDAO servicioDao;
    
    @Transactional(readOnly = true)
    public List<Servicio> obtenerServiciosEliminados() {
        return servicioDao.findByEliminadoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listaServicios() {
        // Obtener la lista de servicios excluyendo aquellos con eliminado=true
        return servicioDao.findByEliminadoFalse();
    }

    @Override
    @Transactional
    public void registrar(Servicio servicio) {
        servicioDao.save(servicio);
    }

   @Override
    @Transactional
    public void eliminar(Servicio servicio) {
        // Cargar el servicio desde la base de datos para evitar problemas con el contexto de persistencia
        Servicio servicioPersistente = servicioDao.findById(servicio.getId()).orElse(null);

        // Verificar si el servicio existe antes de intentar eliminar
        if (servicioPersistente != null) {
            // Cambiar el atributo 'eliminado' a true
            servicioPersistente.setEliminado(true);
            // Establecer la fecha de eliminación como la fecha y hora actual
            servicioPersistente.setFecha(LocalDate.now());
            servicioDao.save(servicioPersistente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio localizarServicio(Servicio servicio) {
        return servicioDao.findById(servicio.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio obtenerServicioPorId(Long id) {
        return servicioDao.findById(id).orElse(null);
    }
    
    @Transactional
    public void restaurar(Servicio servicio) {
        // Cargar el servicio desde la base de datos para evitar problemas con el contexto de persistencia
        Servicio servicioPersistente = servicioDao.findById(servicio.getId()).orElse(null);

        // Verificar si el servicio existe antes de intentar restaurar
        if (servicioPersistente != null) {
            // Cambiar el atributo 'eliminado' a false
            servicioPersistente.setEliminado(false);
            // Guardar el servicio actualizado
            servicioDao.save(servicioPersistente);
        }
    }
}
