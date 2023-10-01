package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Long>{
    
}