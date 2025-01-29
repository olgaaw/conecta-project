package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateContactoDto;
import com.salesianos.conecta.error.ContactoNotFoundException;
import com.salesianos.conecta.model.Contacto;
import com.salesianos.conecta.model.ContactoPK;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Trabajador;
import com.salesianos.conecta.repository.ContactoRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final ProfesorRepository profesorRepository;
    private final TrabajadorRepository trabajadorRepository;

    public List<Contacto> findAll() {
        List<Contacto> result = contactoRepository.findAll();
        if (result.isEmpty()) {
            throw new ContactoNotFoundException("No existen contactos con esos criterios de búsqueda");
        }

        return result;
    }

    public Contacto findById(ContactoPK id) {
        return contactoRepository.findById(id).orElseThrow(() -> new ContactoNotFoundException("No existe ningún contacto con el id"+id));
    }

    public Contacto save(CreateContactoDto dto) {
        Profesor profesor = profesorRepository.findById(dto.profesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Trabajador trabajador = trabajadorRepository.findById(dto.trabajadorId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        Contacto contacto = Contacto.builder()
                .fecha(dto.getFecha())
                .canal(dto.getCanal())
                .resumen(dto.getResumen())
                .profesor(profesor)
                .trabajador(trabajador)
                .build();
        return contactoRepository.save(contacto);
    }

    public Contacto edit(Contacto contacto, ContactoPK id) {
        return contactoRepository.findById(id)
                .map(old -> {
                    old.setFecha(contacto.getFecha());
                    old.setCanal(contacto.getCanal());
                    old.setResumen(contacto.getResumen());
                    return contactoRepository.save(contacto);

                }).orElseThrow(() -> new ContactoNotFoundException("No existe contacto con el id"+id));
    }

    public void delete(ContactoPK id) {
        contactoRepository.deleteById(id);
    }


}