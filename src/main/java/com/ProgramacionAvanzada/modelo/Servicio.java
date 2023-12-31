package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "servicio")
@Getter
@Setter
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nombre;

    @NotBlank
    private String descripcion;

    @Min(0)
    private float precio;

    @ColumnDefault("false") // Valor por defecto
    private boolean eliminado;

    private LocalDate fechaEliminado;
    
    //private float subTotalRepuesto;

    @ManyToMany
    @JoinTable(
        name = "servicio_repuesto",
        joinColumns = @JoinColumn(name = "servicio_id"),
        inverseJoinColumns = @JoinColumn(name = "repuesto_id")
    )
    private Set<Repuesto> repuestos = new HashSet<>();

    public void agregarRepuesto(Repuesto repuesto) {
    if (repuestos == null) {
        repuestos = new HashSet<>();
    }
    repuestos.add(repuesto);
    repuesto.getServicios().add(this);
    }

    // Constructor, getters, setters, etc.
    
    public float calcularSubTotalRepuesto(){
        float sumaPrecios = 0;

        for (Repuesto repuesto : repuestos) {
            sumaPrecios += repuesto.getPrecio();
        }

        return sumaPrecios;
    }
    
    
}
