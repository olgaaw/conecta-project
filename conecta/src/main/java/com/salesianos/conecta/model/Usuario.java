package com.salesianos.conecta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
@Where(clause = "deleted = false")
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "El usuario no puede estar vacío")
    private String username;

    @NotNull(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull(message = "El rol no puede estar vacío")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profesor_id")
    @ToString.Exclude
    private Profesor profesor;

    @Builder.Default
    private boolean deleted = false;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}