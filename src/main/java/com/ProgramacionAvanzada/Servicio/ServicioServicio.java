
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Servicio;
import java.util.List;

public interface ServicioServicio {
    public List<Servicio> listaServicios();
    public void registrar(Servicio ordenDeTrabajo);
    public void eliminar(Servicio ordenDeTrabajo);
    public Servicio localizarServicio(Servicio servicio);

    public Servicio obtenerServicioPorId(Long id);
    
}
