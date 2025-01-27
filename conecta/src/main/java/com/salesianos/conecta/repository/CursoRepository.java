package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
