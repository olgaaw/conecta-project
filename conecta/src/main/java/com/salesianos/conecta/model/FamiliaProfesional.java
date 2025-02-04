package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class FamiliaProfesional {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "familiaProfesional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<Titulo> titulos = new ArrayList<>();

    @ManyToMany(mappedBy = "familiasProfesionales",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Empresa>empresas = new HashSet<>();

    public FamiliaProfesional(String nombre) {
    }

    // Métodos helpers

    public void addTitulo(Titulo t) {
        t.setFamiliaProfesional(this);
        this.getTitulos().add(t);
    }

    public void removeTitulo(Titulo t) {
        this.getTitulos().remove(t);
        t.setFamiliaProfesional(null);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FamiliaProfesional that = (FamiliaProfesional) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
