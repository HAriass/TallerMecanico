package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "servicio")
@Data
public class Servicio implements Serializable{
    
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
   
    
}
