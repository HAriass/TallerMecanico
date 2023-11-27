
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Repuesto;
import java.util.List;

public interface RepuestoServicio {
    public void registrar(Repuesto repuesto);
    public void eliminar(Repuesto repuesto);
    public List<Repuesto> listaRepuestos();
    public Repuesto localizarRepuesto(Repuesto repuesto);

    public Repuesto obtenerRepuestoPorId(Long id);

    public List<Repuesto> buscarPorNombre(String nombre);
}
