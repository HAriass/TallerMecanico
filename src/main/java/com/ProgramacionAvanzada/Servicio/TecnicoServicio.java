package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Tecnico;
import java.util.List;


public interface TecnicoServicio {
    public List<Tecnico> listaTecnicos();
    public void registrar(Tecnico tecnico);
    public void eliminar(Tecnico tecnico);
    public Tecnico localizarTecnico(Tecnico tecnico);
}
