package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tecnico")
@Getter
@Setter
public class Tecnico implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank
    @Pattern(regexp = "^[a-z]+$", message = "Escriba el nombre en minuscula sin numeros")
    private String nombre;
    
    @NotBlank
    @Pattern(regexp = "^[a-z]+$", message = "Escriba el apellido en minuscula sin numeros")
    private String apellido;
    
    @NotBlank
    @Pattern(regexp = "^[1-9]+$", message = "Numero Incorrecto")
    private String telefono;
    
    @Email
    private String email;
    
    @Pattern(regexp = "^[a-z0-9\\s]*$", message = "Controle formato del tipo Sarmiento 123")
    private String direccion;

    private boolean estado;
    
    @NotBlank
    @Column(unique=true)
    private String dni;
    
    
    
    
}
