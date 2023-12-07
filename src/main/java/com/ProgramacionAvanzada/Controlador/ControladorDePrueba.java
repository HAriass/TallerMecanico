
package com.ProgramacionAvanzada.Controlador;

import com.ProgramacionAvanzada.modelo.Repuesto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControladorDePrueba {
    
    @GetMapping("/tomandoDesdePostman")
    @ResponseBody
    public Repuesto tomarDesdePostman(){
        Repuesto repuesto = new Repuesto();
        repuesto.setId(1L);
        repuesto.setNombre("paragolpe");
        repuesto.setDescripcion("descripcion ...");
        repuesto.setCantidad(10);
        repuesto.setPrecio(2000);
        return repuesto;
    }
}
