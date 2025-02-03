package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Demanda {

    @Id
    @GeneratedValue
    private Long id;

    private int cantidadAlumnos;

    private String requisitos;

    @ManyToOne
    @JoinColumn(name = "empresa_id",
            foreignKey = @ForeignKey(name = "fk_empresa_id"))
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "convocatoria_id",
            foreignKey = @ForeignKey(name = "fk_convocatoria_id"))
    private Convocatoria convocatoria;

    @ManyToOne
    @JoinColumn(name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_curso_id"))
    private Curso curso;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Demanda demanda = (Demanda) o;
        return getId() != null && Objects.equals(getId(), demanda.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
