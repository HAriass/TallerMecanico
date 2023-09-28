
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Marca;
import java.util.List;



public interface MarcaServicio {

    public List<Marca> listaMarcas();
    public void registrar(Marca marca);
    public void eliminar(Marca marca);
    public Marca localizarMarca(Marca marca);
    
}
