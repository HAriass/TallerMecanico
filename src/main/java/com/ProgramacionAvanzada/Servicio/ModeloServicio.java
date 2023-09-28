package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Modelo;
import java.util.List;


public interface ModeloServicio {
    
    public List<Modelo> listaModelos();
    public void registrar(Modelo modelo);
    public void eliminar(Modelo modelo);
    public Modelo localizarModelo(Modelo modelo);
}
