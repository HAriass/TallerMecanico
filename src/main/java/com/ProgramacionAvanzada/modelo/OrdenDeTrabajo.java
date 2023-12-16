
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
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ordenDeTrabajo")
@Getter
@Setter
public class OrdenDeTrabajo implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private float impuesto;
    
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

    private LocalDate fechaCreacion = LocalDate.now();
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaEntrega;

   

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
        
    private float subTotal;
    
    private int descuento;
    
    private float total;
    
    @ColumnDefault("false")
    private boolean eliminado;

    private LocalDate fechaEliminado;
    
    private String informacionRelevante;
    
    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }
    
    public float calcularSubTotal() {
    float subTotal = 0;

    for (Servicio servicio : servicio) {
        // Sumar el precio del servicio
        subTotal += servicio.getPrecio();

        // Sumar los precios de los repuestos asociados al servicio
        for (Repuesto repuesto : servicio.getRepuestos()) {
            subTotal += repuesto.getPrecio();
        }
    }

    return subTotal;
}


public void calcularTotal() {

    subTotal = calcularSubTotal();
    // Aplicar el impuesto al subTotal con descuento
    float subtotalConImpuesto = subTotal + (subTotal * (impuesto / 100));
    
    // Restar el descuento al subtotal
    float totalConDescuento = subtotalConImpuesto - (subtotalConImpuesto * (descuento / 100));
    // Asignar el total calculado redondeado a dos decimales
    total = (float) (Math.round(totalConDescuento * 100.0) / 100.0);
    
}




    public long calcularDiferenciaFechas() {
       
        if (fechaEntrega != null) {
            Duration duracion = Duration.between(fechaCreacion.atStartOfDay(), fechaEntrega.atStartOfDay());
            return duracion.toDays();
        } else {
            // Si fechaEntrega es nula, retorna 0 o el valor que consideres apropiado.
            return 0;
        }
    }

    public Tecnico getTecnico() {
        return tecnico;
    }
}
