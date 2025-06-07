package com.microservice.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor; 
import lombok.Data;

@Entity 
@Table(name = "usuario")
@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(nullable=false)
    @NotBlank(message="El nombre son de caracter obligatorio")
    @Size(min=2, max=100,message="El nombre deben tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message="Los apellidos son de caracter obligatorio")
    @Size(min=2, max=100,message="Los apellidos deben tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String apellidos;

    @Column(name="rut", unique=true,length=13,nullable = false)
    @NotBlank(message = "Rut es obligatorio")
    @Pattern(
        regexp ="^[0-9]{7,8}-[0-9kk]$",
        message = "El rut debe ser en formato chileno"
    )
    private String rut;

    @Column(nullable = false)
    @Min(value = 0, message = "La edad no puede ser menor que 0")
    @Max(value = 120, message = "La edad no puede ser mayor que 120")
    private Integer edad;


    @Column(nullable=false,unique = true)
    @NotBlank(message="El correo es obligaotrio")
    @Email(message="El formato del correo no es valido")
    private String correo;


}
