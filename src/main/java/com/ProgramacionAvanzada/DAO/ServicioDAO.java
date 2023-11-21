
package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioDAO extends CrudRepository<Servicio, Long>{ 
    // Método para obtener servicios excluyendo aquellos con eliminado=true
    List<Servicio> findByEliminadoFalse();
    @Query("SELECT s FROM Servicio s WHERE s.eliminado = true")
    List<Servicio> findByEliminadoTrue();
}



