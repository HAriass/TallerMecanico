package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Modelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloDAO extends CrudRepository<Modelo, Long>{
    
}
