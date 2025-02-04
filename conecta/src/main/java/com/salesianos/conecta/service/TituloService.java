package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateTituloDto;
import com.salesianos.conecta.dto.GetTituloDto;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.error.TituloNotFoundException;
import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import com.salesianos.conecta.repository.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloService {
    private final TituloRepository tituloRepository;
    private final FamiliaProfesionalRepository familiaProfesionalRepository;

    public List<Titulo> findAll() {
        List<Titulo> result = tituloRepository.findAll();
        if (result.isEmpty()) {
            throw new TituloNotFoundException();
        }

        return result;
    }

    public Titulo findById(Long id) {
        return tituloRepository.findById(id).orElseThrow(() -> new TituloNotFoundException(id));
    }

    public GetTituloDto save(CreateTituloDto titulo) {
       Titulo t = new Titulo();
       t.setNombre(titulo.nombre());
       t.setDuracion(titulo.duracion());
       t.setGrado(titulo.grado());
       t.setFamiliaProfesional(familiaProfesionalRepository.findById(titulo.familiaProfesional().getId())
               .orElseThrow(() -> new FamiliaProfesionalNotFoundException(titulo.familiaProfesional().getId())));

       for (Curso c : titulo.curso()) {
           t.addCurso(c);
       }

       tituloRepository.save(t);

       return GetTituloDto.of(t);
    }



}
