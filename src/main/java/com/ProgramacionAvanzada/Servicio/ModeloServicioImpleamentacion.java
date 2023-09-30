package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.ModeloDAO;
import com.ProgramacionAvanzada.modelo.Modelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModeloServicioImpleamentacion implements ModeloServicio{
    
    @Autowired
    private ModeloDAO modeloDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Modelo> listaModelos() {
        
        // Como lo tenemos como variable, lo casteamos a lista
        return (List<Modelo>) modeloDao.findAll();

    }

    @Override
    public void registrar(Modelo modelo){
        modeloDao.save(modelo);
    }
    
    @Override
    public Modelo localizarModelo(Modelo modelo) {
        return modeloDao.findById(modelo.getId()).orElse(null);
    }

    
    @Override
    public void eliminar(Modelo modelo) {
        modeloDao.delete(modelo);
    }

}
