package com.salesianos.conecta.repos;

import com.salesianos.conecta.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandaRepository extends JpaRepository<Demanda,Long> {
}
