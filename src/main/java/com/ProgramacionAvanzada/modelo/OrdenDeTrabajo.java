
package com.ProgramacionAvanzada.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JoinColumn(name="id_vehiculo")
    private Vehiculo vehiculo;
    
    @ManyToMany
    @JoinTable(
        name = "orden_servicio", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "orden_id"), // Columna de orden en la tabla intermedia
        inverseJoinColumns = @JoinColumn(name = "servicio_id") // Columna de servicio en la tabla intermedia
    )
    private List<Servicio> servicio;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime fechaEntrega;
        
    private float subTotal;
    
    private int descuento;
    
    private float total;
    
    private String informacionRelevante;
    
    public float calcularSubTotal() {
        float suma = 0;
        float suma2 = 0;
        for (Servicio s : servicio) {
            for (Repuesto r: s.getRepuestos()){
                suma2 += r.getPrecio();
            }
        suma += s.getPrecio() + suma2;
        
        }
        return suma;
    }

    public void calcularTotal() {
        subTotal = calcularSubTotal();
        total = subTotal - (subTotal * descuento) / 100;
    }
}
