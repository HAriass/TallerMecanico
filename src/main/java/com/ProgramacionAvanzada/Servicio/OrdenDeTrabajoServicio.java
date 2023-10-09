
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import java.util.List;


public interface OrdenDeTrabajoServicio {
    public List<OrdenDeTrabajo> listaOrdenDeTrabajo();
    public void registrar(OrdenDeTrabajo ordenDeTrabajo);
    public void eliminar(OrdenDeTrabajo ordenDeTrabajo);
    public OrdenDeTrabajo localizarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo);
    public OrdenDeTrabajo obtenerOrdenDeTrabajoPorId(Long id);
}
