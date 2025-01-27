package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Titulo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private int duracion;

    private String grado;

    @OneToMany(mappedBy = "titulo")
    @Builder.Default
    @ToString.Exclude
    private List<Curso> cursos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familiaProfesional_id",
            foreignKey = @ForeignKey(name = "fk_familiaProfesional_id"))
    private FamiliaProfesional familiaProfesional;

    // Métodos helpers

    public void addCurso(Curso c) {
        c.setTitulo(this);
        this.getCursos().add(c);
    }

    public void removeCurso(Curso c) {
        this.getCursos().remove(c);
        c.setTitulo(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Titulo titulo = (Titulo) o;
        return getId() != null && Objects.equals(getId(), titulo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
