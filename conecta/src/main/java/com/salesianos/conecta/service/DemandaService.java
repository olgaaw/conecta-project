package com.salesianos.conecta.service;

import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.repository.DemandaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandaService {

    private final DemandaRepository demandaRepository;

    public List<Demanda> findAll(){

        List<Demanda> result = demandaRepository.findAll();

        if (result.isEmpty()){
            throw new EntityNotFoundException("No se han encontrado demandas con esos parametros");
        }

        return result;

    }

    public Demanda findById(Long id){
        return demandaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado una demanda con el id: " + id));
    }

    public Demanda save(Demanda demanda){
        return demandaRepository.save(Demanda.builder()
                        .cantidadAlumnos(demanda.getCantidadAlumnos())
                        .requisitos(demanda.getRequisitos())
                .build());
    }

    public void delete(Long id){
        demandaRepository.deleteById(id);
    }

}
