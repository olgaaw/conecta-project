package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Curso;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Demanda d WHERE d.curso.id = :cursoId")
    void deleteDemandasByCursoId(@Param("cursoId") Long cursoId);
}
