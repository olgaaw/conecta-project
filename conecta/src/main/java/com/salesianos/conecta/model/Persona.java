package com.salesianos.conecta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
    @Id @GeneratedValue
    protected Long id;

    @NotNull(message = "El nombre no puede estar vacio")
    protected String nombre;

    @NotNull(message = "El apellido no puede estar vacio")
    protected String apellidos;

    @NotNull(message = "El email no puede estar vacio")
    protected String email;

    @NotNull(message = "El telefono no puede estar vacio")
    protected int telefono;
}
