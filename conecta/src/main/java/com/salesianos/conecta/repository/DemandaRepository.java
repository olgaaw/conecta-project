package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandaRepository extends JpaRepository<Demanda,Long> {
    @Modifying
    @Query("""
            DELETE FROM Demanda d 
            WHERE d.curso.id = :cursoId
            """)
    void deleteByCursoId(@Param("cursoId") Long cursoId);

    @Query("""
            select d from Demanda d
            where d.empresa.id = :empresaId
            """)
    List<Demanda> findByEmpresaId(@Param("empresaId") Long empresaId);
}
