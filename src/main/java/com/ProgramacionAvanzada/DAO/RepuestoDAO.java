package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Repuesto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoDAO extends CrudRepository<Repuesto, Long>{
    
    List<Repuesto> findByNombreContainingIgnoreCase(String nombre);
    
}