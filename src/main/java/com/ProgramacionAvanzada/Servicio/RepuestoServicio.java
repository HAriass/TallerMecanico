
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Repuesto;
import java.util.List;
import org.springframework.stereotype.Service;

public interface RepuestoServicio {
    public void registrar(Repuesto repuesto);
    public void eliminar(Repuesto repuesto);
    public List<Repuesto> listaRepuestos();
}
