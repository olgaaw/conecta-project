package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private int horasEmpresa;

    @ManyToMany(mappedBy = "cursos", fetch = FetchType.LAZY)
    private Set<Profesor> profesores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "titulo_id",
    foreignKey = @ForeignKey(name = "fk_titulo_curso"))
    private Titulo titulo;


    //helpers
    public void addProfesor(Profesor p) {
        this.profesores.add(p);
        p.getCursos().add(this);
    }

    public void removeProfesor(Profesor p) {
        this.profesores.remove(p);
        p.getCursos().remove(this);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Curso curso = (Curso) o;
        return getId() != null && Objects.equals(getId(), curso.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
