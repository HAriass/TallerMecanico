
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.RepuestoDAO;
import com.ProgramacionAvanzada.modelo.Repuesto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


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
    
}
