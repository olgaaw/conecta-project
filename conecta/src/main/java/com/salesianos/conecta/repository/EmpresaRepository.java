package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("""
            select e from Empresa e
            where size(e.demandas) > 1
            """)
    List<Empresa> findEmpresasVariasDemandas();
}
