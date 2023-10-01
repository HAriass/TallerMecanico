package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Tecnico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoDAO extends CrudRepository<Tecnico, Long>{
    
}
