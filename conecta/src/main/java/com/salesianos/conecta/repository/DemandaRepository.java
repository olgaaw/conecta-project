package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandaRepository extends JpaRepository<Demanda,Long> {
}
