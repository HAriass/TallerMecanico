
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Servicio;
import java.util.List;

public interface ServicioServicio {
    List<Servicio> listaServicios();
    void registrar(Servicio ordenDeTrabajo);
    void eliminar(Servicio ordenDeTrabajo);
    Servicio localizarServicio(Servicio servicio);
    Servicio obtenerServicioPorId(Long id);
}