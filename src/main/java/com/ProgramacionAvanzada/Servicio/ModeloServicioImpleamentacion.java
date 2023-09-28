package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.ModeloDAO;
import com.ProgramacionAvanzada.modelo.Modelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class ModeloServicioImpleamentacion {
    
    @Autowired
    private ModeloDAO modeloDao;
    
    @Transactional(readOnly = true)
    public List<Modelo> listaMarcas() {
        
        // Como lo tenemos como variable, lo casteamos a lista
        return (List<Modelo>) modeloDao.findAll();

    }

    public void registrar(Modelo modelo){
        modeloDao.save(modelo);
    }
    
    
    public Modelo localizarMarca(Modelo marca) {
        return modeloDao.findById(marca.getId()).orElse(null);
    }

    
    public void eliminar(Modelo marca) {
        modeloDao.delete(marca);
    }
    
    
}
