package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ContactoRepository extends JpaRepository<Contacto, ContactoPK> {

    @Query("""
            select c from Contacto c
            where c.contactoPK.profesor_id = :profesorId and c.contactoPK.trabajador_id = :trabajadorId
            """)
    Optional<Contacto> findByIdSimple(@Param("profesorId") Long profesorId, @Param("trabajadorId") Long trabajadorId);

    @Modifying
    @Query("""
            update Contacto c
            set c.deleted = true
            where c.contactoPK.profesor_id = :profesorId and c.contactoPK.trabajador_id = :trabajadorId
            """)
    void deletedByIdSimple(@Param("profesorId") Long profesorId, @Param("trabajadorId") Long trabajadorId);
}
