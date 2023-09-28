
package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name = "marca")
@Data
public class Marca implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id // Indica que id_individuo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores de clave primaria
    private Long id;
    
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre debe contener solo letras de la A a la Z")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
    
    private boolean estado;

    public Marca() {
    }

    public Marca(Long id, String nombre, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    
    
}
