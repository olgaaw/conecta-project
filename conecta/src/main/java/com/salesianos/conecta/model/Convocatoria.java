package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Convocatoria {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cursoEscolar")
    private String cursoEscolar;

    private String nombre;

    @OneToMany(mappedBy = "convocatoria", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Demanda>demandas = new ArrayList<>();

    // Métodos helpers

    public void adddemanda(Demanda d) {
        d.setConvocatoria(this);
        this.getDemandas().add(d);
    }

    public void removeProducto(Demanda p) {
        this.getDemandas().remove(p);
        p.setConvocatoria(null);
    }


}
