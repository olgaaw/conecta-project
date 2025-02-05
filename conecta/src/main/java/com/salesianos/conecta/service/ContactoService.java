package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.contacto.CreateContactoDto;
import com.salesianos.conecta.dto.contacto.GetContactoDto;
import com.salesianos.conecta.error.ContactoNotFoundException;
import com.salesianos.conecta.error.ProfesorNotFoundException;
import com.salesianos.conecta.error.UsuarioNotFoundException;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.repository.ContactoRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
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

    public Contacto save(CreateContactoDto dto) {

        Contacto c = new Contacto();

        c.setFecha(dto.fecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        c.setCanal(dto.canal());
        c.setResumen(dto.resumen());
        c.setProfesor(profesorRepository.findById(dto.profesor().getId())
                .orElseThrow(() -> new ProfesorNotFoundException(dto.profesor().getId())));
        c.setTrabajador(trabajadorRepository.findById(dto.trabajador().getId())
                .orElseThrow(() -> new UsuarioNotFoundException(dto.trabajador().getId())));

        return contactoRepository.save(c);

    }

    public Contacto edit(CreateContactoDto contacto, ContactoPK id) {
        Contacto contactoExistente = contactoRepository.findById(id)
                .orElseThrow(() -> new ContactoNotFoundException("No se encontró el contacto con id " + id));

        contactoExistente.setFecha(contacto.fecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        contactoExistente.setCanal(contacto.canal());
        contactoExistente.setResumen(contacto.resumen());
        contactoExistente.setProfesor(profesorRepository.findById(contacto.profesor().getId())
                .orElseThrow(() -> new ProfesorNotFoundException(contacto.profesor().getId())));
        contactoExistente.setTrabajador(trabajadorRepository.findById(contacto.trabajador().getId())
                .orElseThrow(() -> new UsuarioNotFoundException(contacto.trabajador().getId())));

        return contactoRepository.save(contactoExistente);

    }

    public void delete(ContactoPK id) {
        contactoRepository.findById(id)
                .orElseThrow(() -> new ContactoNotFoundException());
        contactoRepository.deleteById(id);
    }


}