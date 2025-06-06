package com.microservice.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constrains.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstrucor; 
import lombok.Data;

import java.util.Date;

@Entity 
@Table(name = "usuario")
@Data 
@Builder
@NoArgsConstrucor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(name="rut", unique=true,length=13,nullable = false)
    @NotBlank(message = "Rut es obligatorio")
    @Pattern(
        regexp ="^[0-9]{7,8}-[0-9kk]$",
        message = "El rut debe ser en formato chileno"
    )
    private String rut;

    @column(nullable=false)
    @NotBlanck(message = "El nombre son de caracter obligatorio")
    @size(min=2, max=100,message="El nombre deben tener entre 2 y 100 caracteres")
    private String nombre;

    @Column(message= "Los apellidos son de caracter obligatorio")
    @size(min=2, max=100,message="Los apellidos deben tener entre 2 y 100 caracteres")


}
