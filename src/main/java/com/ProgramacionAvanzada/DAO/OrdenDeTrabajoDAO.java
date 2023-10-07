package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeTrabajoDAO extends CrudRepository<OrdenDeTrabajo, Long>{
    
}