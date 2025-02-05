package com.salesianos.conecta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
@SQLDelete(sql = "UPDATE contacto SET deleted = true WHERE profesor_id = ? AND trabajador_id = ?")
@Where(clause = "deleted = false")
public class Usuario {
    @Column
    @Id @GeneratedValue
    private Long id;

    @Column
    @NotNull(message = "El usuario no puede estar vacio")
    private String username;

    @Column
    @NotNull(message = "La contraseña no puede estar vacio")
    private String password;

    @Column
    @NotNull(message = "El rol no puede estar vacio")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profesor_id")
    @ToString.Exclude
    private Profesor profesor;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
