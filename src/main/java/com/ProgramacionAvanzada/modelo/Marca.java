
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
import lombok.Data;

@Entity
@Table(name = "marca")
@Data
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
 
}

/**
 VALIDACIONES SPRING STAR VALIDATION
 * 
 * @NotNull -- para valores no nulos
 * @NotBlank -- para que los string no esten en blanco o vacios
 * @ValueMin -- valor minimo de un precio por ejemplo
 * @ValuMax
 * @Pattern -- para expresiones regulares
 * para cualquiera de estas anotaciones puedo agregar un mensaje como parametro para indicar el error de manera 
 * mas descriptiva. Y en el post seria:
 *  if(bindingresult.hasErrors()) -- si tiene error:
 *          return (new Message(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.OK);
 * 
 * Para validar en Post
 * @Valid seguido de lo que queremos validar ej: @Valid marca
 * BindingResult binding result -- como parametro, nos permite conocer si existe algun error en la estructura 
 * y controlarla
 * 
 * Utilizacion de bindingresult 
 * 
 * if(bindingresult.hasErrors()) -- si tiene error:
 *          return (new message("mensaje"), HttpStatus.Bad_REQUEST);
 */