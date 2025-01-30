package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoRepository extends JpaRepository<Contacto, ContactoPK> {


}
