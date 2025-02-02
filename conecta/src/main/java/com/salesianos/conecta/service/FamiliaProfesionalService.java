package com.salesianos.conecta.service;

import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamiliaProfesionalService {

    private final FamiliaProfesionalRepository familiaProfesionalRepository;

    public List<FamiliaProfesional> findAll(){

        List<FamiliaProfesional> result = familiaProfesionalRepository.findAll();

        if (result.isEmpty()){
            throw new DemandaNotFoundException();
        }

        return result;

    }
/*
    public FamiliaProfesional findById(Long id){
        return familiaProfesionalRepository.findById(id)
                .orElseThrow(() -> new DemandaNotFoundException(id));
    }

    public FamiliaProfesional save(Demanda demanda){
        return familiaProfesionalRepository.save(FamiliaProfesional.builder()

                .build());
    }

    public void delete(Long id){
        familiaProfesionalRepository.deleteById(id);
    }
*/
}
