package com.salesianos.conecta.model;

import jakarta.persistence.*;
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

    protected String nombre;

    protected String appelidos;

    protected String email;

    protected int telefono;
}
