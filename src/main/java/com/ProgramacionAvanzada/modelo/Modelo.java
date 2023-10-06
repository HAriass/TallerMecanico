package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Importa la anotación JoinColumn
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name = "modelo")
@Data
public class Modelo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Pattern(regexp = "^[a-z1-9]+$", message = "El modelo debe contener solo caracteres a-z (minuscula) y numeros del 1 al 9")
    @NotBlank(message = "")
    @Column(unique=true)
    private String nombre;
    
    @ManyToOne // Indica una relación muchos a uno con la entidad Marca
    @JoinColumn(name = "marca_id") // Nombre de la columna que contiene la clave primaria
    private Marca marca; 
    
    @NotBlank(message = "El año no debe estar vacio")
    //@Min(value = 1917, message = "")
    //@Max(value = 2023, message = "Porfavor ingrese un año valido")
    private String anio;
    
    private boolean estado;

}
