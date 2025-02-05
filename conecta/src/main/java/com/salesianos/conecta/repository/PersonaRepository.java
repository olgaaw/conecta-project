package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
