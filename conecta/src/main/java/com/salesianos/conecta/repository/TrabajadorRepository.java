package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    @Query("""
            SELECT t FROM Trabajador t 
            WHERE t.area = :area
            """)
    List<Trabajador> findTrabajadoresByArea(@Param("area") String area);
}
