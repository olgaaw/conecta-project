package com.salesianos.conecta.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Empresa {


    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Trabajador> trabajadores = new ArrayList<>();
}
