
package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Marca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaDAO extends CrudRepository<Marca, Long>{
      
}
