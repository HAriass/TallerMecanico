package com.ProgramacionAvanzada.DAO;

import com.ProgramacionAvanzada.modelo.OrdenDeTrabajo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeTrabajoDAO extends CrudRepository<OrdenDeTrabajo, Long>{

    public List<OrdenDeTrabajo> findByTecnicoIdAndFechaCreacionBetween(Long idTecnico, LocalDate fechaInicio, LocalDate fechaFin);
    public List<OrdenDeTrabajo> findByFechaCreacionBetween(LocalDate fechaInicio,LocalDate fechaFin);
    List<OrdenDeTrabajo> findByEliminadoFalse(); 
    @Query("SELECT o FROM OrdenDeTrabajo o WHERE o.eliminado = true")
    public List<OrdenDeTrabajo> findByEliminadoTrue();
}