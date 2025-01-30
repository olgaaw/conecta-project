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
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cif;

    private String direccion;

    private String coordenadas;

    private String nombre;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Trabajador> trabajadores = new ArrayList<>();


    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Demanda> demandas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="empresa_id"),
            inverseJoinColumns = @JoinColumn(name="familiaProfesional_id")
    )
    @Builder.Default
    private Set<FamiliaProfesional> familiasProfesionales = new HashSet<>();

    // Métodos helpers de Trabajador

    public void addTrabajador(Trabajador t) {
        t.setEmpresa(this);
        this.getTrabajadores().add(t);
    }

    public void removeTrabajador(Trabajador t) {
        this.getTrabajadores().remove(t);
        t.setEmpresa(null);
    }

    // Métodos helpers de Demanda

    public void addDemanda(Demanda d) {
        d.setEmpresa(this);
        this.getDemandas().add(d);
    }

    public void removeDemanda(Demanda d) {
        this.getDemandas().remove(d);
        d.setEmpresa(null);
    }

    // Métodos helpers de Familia profesional

    public void addFamiliaProfesional(FamiliaProfesional f) {
        this.getFamiliasProfesionales().add(f);
        f.getEmpresas().add(this);
    }

    public void removeFamiliaProfesional(FamiliaProfesional f) {
        f.getEmpresas().remove(this);
        this.getFamiliasProfesionales().remove(f);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Empresa empresa = (Empresa) o;
        return getId() != null && Objects.equals(getId(), empresa.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
