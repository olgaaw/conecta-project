package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactoRepository extends JpaRepository<Contacto, ContactoPK> {
    @Query("""
            select c from Contacto  c
            where c.contactoPK.id = :id
            """)
    Optional<Contacto> findByIdSimple(@Param("id") Long id);

    @Modifying
    @Query("""
            delete from Contacto c
            where c.contactoPK.id = :id
            """)
    void deletedByIdSimple(@Param("id") Long id);
}
