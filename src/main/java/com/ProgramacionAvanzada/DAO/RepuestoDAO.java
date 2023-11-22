package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Repuesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoDAO extends CrudRepository<Repuesto, Long>{
    
}