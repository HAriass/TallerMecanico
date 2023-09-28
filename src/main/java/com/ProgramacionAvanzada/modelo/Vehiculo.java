package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    
    private String patente;
    private boolean estado;

    
}
