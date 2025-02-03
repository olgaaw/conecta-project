package com.salesianos.conecta.service;

import com.salesianos.conecta.error.ProfesorNotFoundException;
import com.salesianos.conecta.error.TituloNotFoundException;
import com.salesianos.conecta.model.Profesor;
import com.salesianos.conecta.model.Titulo;
import com.salesianos.conecta.repository.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloService {
    private final TituloRepository tituloRepository;

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
}
