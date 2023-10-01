
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.TecnicoDAO;
import com.ProgramacionAvanzada.modelo.Tecnico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoServicioImpleamentacion implements TecnicoServicio{
    
    @Autowired
    private TecnicoDAO tecnicoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Tecnico> listaTecnicos() {
        return (List<Tecnico>) tecnicoDao.findAll();
    }

    @Override
    public void registrar(Tecnico tecnico) {
        tecnicoDao.save(tecnico);
    }

    @Override
    public void eliminar(Tecnico tecnico) {
        tecnicoDao.delete(tecnico);
    }

    @Override
    public Tecnico localizarTecnico(Tecnico tecnico) {
        return tecnicoDao.findById(tecnico.getId()).orElse(null);
    }
    
}
