package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table
public class Vehiculo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @ManyToOne // Indica una relación uno a mucho con marca
    @JoinColumn(name = "id_marca") // Nombre de la columna que contiene la clave primaria
    private Marca marca;
    
    @ManyToOne // Indica una relación uno a mucho con modelo
    @JoinColumn(name = "id_modelo") // Nombre de la columna que contiene la clave primaria
    private Modelo modelo;
    
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9]+$", message = "La pantente debe contener letras mayusculas y numero 1-9")
    @Column(unique=true)
    private String patente;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    private boolean estado;

    
}
