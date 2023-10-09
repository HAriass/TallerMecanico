package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Modelo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloDAO extends CrudRepository<Modelo, Long>{

    public List<Modelo> findByMarcaId(Long marcaId);
    
}
