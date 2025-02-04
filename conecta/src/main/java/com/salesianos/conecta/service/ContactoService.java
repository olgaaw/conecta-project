package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.dto.GetContactoDto;
import com.salesianos.conecta.error.ContactoNotFoundException;
import com.salesianos.conecta.error.ProfesorNotFoundException;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Trabajador;
import com.salesianos.conecta.repository.ContactoRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
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
                .orElseThrow(() -> new ProfesorNotFoundException("Profesor no encontrado")));
        c.setTrabajador(trabajadorRepository.findById(dto.trabajador().getId())
                .orElseThrow(() -> new TransactionException("Trabajador no encontrado")));

        contactoRepository.save(c);

        return GetContactoDto.of(c);
    }

    public Contacto edit(Contacto contacto, ContactoPK id) {
        return contactoRepository.findById(id)
                .map(old -> {
                    old.setFecha(contacto.getFecha());
                    old.setCanal(contacto.getCanal());
                    old.setResumen(contacto.getResumen());
                    old.setProfesor(contacto.getProfesor());
                    old.setTrabajador(contacto.getTrabajador());
                    return contactoRepository.save(contacto);

                }).orElseThrow(() -> new ContactoNotFoundException("No existe contacto con el id"+id));
    }

    public void delete(ContactoPK id) {
        contactoRepository.deleteById(id);
    }


}