
package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordenDeTrabajo")
@Getter
@Setter
public class OrdenDeTrabajo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    
    @ManyToMany
    @JoinTable(
        name = "orden_servicio", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "orden_id"), // Columna de orden en la tabla intermedia
        inverseJoinColumns = @JoinColumn(name = "servicio_id") // Columna de servicio en la tabla intermedia
    )
    private List<Servicio> servicio;

    
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$") // dia mes anio
    private String fechaCreacion;
    
    
    private String informacionRelevante;
}
