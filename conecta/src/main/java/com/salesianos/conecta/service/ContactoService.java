package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.error.*;
import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.ContactoRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final EntityManager entityManager;
    private final TrabajadorRepository trabajadorRepository;
    private final ProfesorRepository profesorRepository;

    public List<Contacto> findAll(Boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedContactoFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Contacto> result = contactoRepository.findAll();
        return result;
    }

    public Contacto findById(ContactoPK id) {
        return contactoRepository.findById(id).orElseThrow(() -> new ContactoNotFoundException("No existe ningún contacto con el id"+id));
    }

    public GetContactoDto save(CreateContactoDto dto) {

        Contacto c = new Contacto();

        c.setFecha(dto.fecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        c.setCanal(dto.canal());
        c.setResumen(dto.resumen());
        c.setProfesor(profesorRepository.findById(dto.profesor().getId())
                .orElseThrow(() -> new ProfesorNotFoundException(dto.profesor().getId())));
        c.setTrabajador(trabajadorRepository.findById(dto.trabajador().getId())
                .orElseThrow(() -> new UsuarioNotFoundException(dto.trabajador().getId())));

        contactoRepository.save(c);

        return GetContactoDto.of(c);
    }

    public GetContactoDto edit(CreateContactoDto contacto, ContactoPK id) {
        // Busca el contacto utilizando el ContactoPK
        Contacto contactoExistente = contactoRepository.findById(id)
                .orElseThrow(() -> new ContactoNotFoundException("No se encontró el contacto con id " + id));

        // Actualiza los campos del contacto
        contactoExistente.setFecha(contacto.fecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        contactoExistente.setCanal(contacto.canal());
        contactoExistente.setResumen(contacto.resumen());
        contactoExistente.setProfesor(profesorRepository.findById(contacto.profesor().getId())
                .orElseThrow(() -> new ProfesorNotFoundException(contacto.profesor().getId())));
        contactoExistente.setTrabajador(trabajadorRepository.findById(contacto.trabajador().getId())
                .orElseThrow(() -> new UsuarioNotFoundException(contacto.trabajador().getId())));

        // Guarda el contacto actualizado
        contactoRepository.save(contactoExistente);

        return GetContactoDto.of(contactoExistente);
    }

    public void delete(ContactoPK id) {
        contactoRepository.deleteById(id);
    }


}