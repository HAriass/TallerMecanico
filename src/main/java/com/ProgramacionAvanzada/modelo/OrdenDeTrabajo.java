
package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "ordenDeTrabajo")
@Data
public class OrdenDeTrabajo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    /**
    
    @OneToMany
    @JoinColumn(name = "id_servicio")
    private List<Servicio> servicio;
    **/
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$") // dia mes anio
    private String fechaCreacion;
    
    
    private String informacionRelevante;
}
