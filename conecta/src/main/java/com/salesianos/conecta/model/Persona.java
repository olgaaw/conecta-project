package com.salesianos.conecta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Persona {
    @Id @GeneratedValue
    protected Long id;

    @Column
    @NotNull(message = "El nombre no puede estar vacio")
    protected String nombre;

    @Column
    @NotNull(message = "El apellido no puede estar vacio")
    protected String apellidos;

    @Column
    @NotNull(message = "El email no puede estar vacio")
    protected String email;

    @Column
    @NotNull(message = "El telefono no puede estar vacio")
    protected int telefono;
}
