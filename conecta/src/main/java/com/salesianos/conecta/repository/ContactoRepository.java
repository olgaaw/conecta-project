package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactoRepository extends JpaRepository<Contacto, ContactoPK> {

    @Query("""
             select c from Contacto c 
             join c.trabajador t
             join t.empresa e
             join e.familiasProfesionales f
             where f.nombre = :nombreFamiliaProfesional
            """
            )
    List<Contacto> findByFamiliaProfesionalNombre(@Param("nombreFamiliaProfesional") String nombreFamiliaProfesional);

}
