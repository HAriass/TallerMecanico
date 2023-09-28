package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.MarcaDAO;
import com.ProgramacionAvanzada.modelo.Marca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarcaServicioImplementacion implements MarcaServicio{
    
    @Autowired
    private MarcaDAO marcaDao;
    

    @Transactional(readOnly = true)
    @Override
    public List<Marca> listaMarcas() {
        
        // Como lo tenemos como variable, lo casteamos a lista
        return (List<Marca>) marcaDao.findAll();

    }

    @Override
    public void registrar(Marca marca) {
        marcaDao.save(marca);
    }

    @Override
    public Marca localizarMarca(Marca marca) {
        return marcaDao.findById(marca.getId()).orElse(null);
    }

    @Override
    public void eliminar(Marca marca) {
        marcaDao.delete(marca);
    }
    
    
}
