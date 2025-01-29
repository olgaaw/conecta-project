package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Profesor extends Persona{

    @OneToOne(mappedBy = "profesor", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    @Builder.Default
    private Set<Curso> cursos = new HashSet<>();

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Contacto> contactos = new ArrayList<>();


    //helpers
    public void addCurso(Curso c) {
       this.cursos.add(c);
       c.getProfesores().add(this);
    }

    public void removeCurso(Curso c) {
       this.cursos.remove(c);
       c.getProfesores().remove(this);
    }


    
    
    



    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Profesor profesor = (Profesor) o;
        return getId() != null && Objects.equals(getId(), profesor.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
