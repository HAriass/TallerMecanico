
package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioDAO extends CrudRepository<Servicio, Long>{
    
}
