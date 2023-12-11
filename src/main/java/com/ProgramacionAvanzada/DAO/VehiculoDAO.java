
package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Vehiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoDAO extends CrudRepository<Vehiculo, Long>{
    
    
}
