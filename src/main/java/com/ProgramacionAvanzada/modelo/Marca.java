
package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "marca")
@Getter
@Setter
public class Marca implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id // Indica que id_individuo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores de clave primaria
    private Long id;
    
    @Pattern(regexp = "^[a-z]+$", message = "La marca debe contener solo caracteres a-z minuscula.")
    @NotBlank(message = "Marca invalida")
    @Column(unique = true)
    private String nombre;
    
    private boolean estado;
    
    private int impuesto;
 
    public int getImpuesto() {
        return impuesto;
    }
}

