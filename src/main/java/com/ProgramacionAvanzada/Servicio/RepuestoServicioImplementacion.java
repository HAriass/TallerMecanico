
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.RepuestoDAO;
import com.ProgramacionAvanzada.modelo.Repuesto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RepuestoServicioImplementacion implements RepuestoServicio {
    
    @Autowired
    private RepuestoDAO repuestoDAO;

    @Override
    public void registrar(Repuesto repuesto) {
        repuestoDAO.save(repuesto);
    }

    @Override
    public void eliminar(Repuesto repuesto) {
        repuestoDAO.delete(repuesto);
    }

    @Override
    public List<Repuesto> listaRepuestos() {
        return (List<Repuesto>) repuestoDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Repuesto localizarRepuesto(Repuesto repuesto) {
        return repuestoDAO.findById(repuesto.getId()).orElse(null);
    }

    @Override
    public Repuesto obtenerRepuestoPorId(Long id) {
        Optional<Repuesto> optionalRepuesto = repuestoDAO.findById(id);
        return optionalRepuesto.orElse(null);
    }
    
     @Override
    public List<Repuesto> buscarPorNombre(String nombre) {
        return repuestoDAO.findByNombreContainingIgnoreCase(nombre);
    }
    
}
