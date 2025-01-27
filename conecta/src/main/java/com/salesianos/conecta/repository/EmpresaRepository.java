package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
