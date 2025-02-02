package com.salesianos.conecta.repository;

import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("""
            SELECT com.salesianos.conecta.dto.GetEmpresaDto(
                e.nombre,
                e.direccion,
                e.familiasProfesionales,
                SIZE(e.demandas)
            )
            FROM Empresa e WHERE SIZE(e.demandas) > 2
            """)
    List<GetEmpresaDto> GetAllDto();

}
