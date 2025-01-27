package com.salesianos.conecta.repos;

import com.salesianos.conecta.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
