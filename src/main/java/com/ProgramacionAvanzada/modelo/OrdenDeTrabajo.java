
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
    
    @DateTimeFormat(pattern = "dd/MM/yyyy") // Añade esta anotación para especificar el formato de entrada
    private LocalDate fechaEntrega;
        
    private float subTotal;
    
    private int descuento;
    
    private float total;
    
    
    private String informacionRelevante;
    
    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }
    
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
        
        // Aplicar el impuesto solo al total
        total = subTotal + (subTotal * (impuesto/100));
        total = total - (total * (descuento/100));
    }

    public long calcularDiferenciaFechas() {
        System.out.println("Calculando diferencia de fechas...");
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
