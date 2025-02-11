package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoRepository extends JpaRepository<Contacto, ContactoPK> {
    @Query("""
            SELECT c FROM Contacto c 
            WHERE c.profesor.id = :profesorId
            """)
    List<Contacto> findContactosByProfesorId(@Param("profesorId") Long profesorId);

    @Query("""
             select c from Contacto c 
             join c.trabajador t
             join t.empresa e
             join e.familiasProfesionales f
             where f.nombre = :nombreFamiliaProfesional
            """
            )
    List<Contacto> findByFamiliaProfesionalNombre(@Param("nombreFamiliaProfesional") String nombreFamiliaProfesional);

    @Query("""
            select c from Contacto c 
            where c.trabajador.empresa.id = :empresaId
            """)
    List<Contacto> findByEmpresaId(@Param("empresaId") Long empresaId);


    @Query("""
            select c from Contacto c
            where c.trabajador.id = :trabajadorId
            """)
    List<Contacto> findByTrabajadorId(@Param("trabajadorId") Long trabajadorId);

    @Query("""
            select c from Contacto c
            where c.profesor.id = :profesorId
            """)
    List<Contacto> findByProfesorId(@Param("profesorId") Long profesorId);






}
