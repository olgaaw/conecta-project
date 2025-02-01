package com.salesianos.conecta.service;

import com.salesianos.conecta.error.ConvocatoriaNotFoundException;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.repository.ConvocatoriaRepository;
import com.salesianos.conecta.repository.DemandaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvocatoriaService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public List<Convocatoria> findAll(){

        List<Convocatoria> result = convocatoriaRepository.findAll();

        if (result.isEmpty()){
            throw new DemandaNotFoundException();
        }

        return result;

    }

    public Convocatoria findById(Long id){
        return convocatoriaRepository.findById(id)
                .orElseThrow(() -> new ConvocatoriaNotFoundException(id));
    }

    /*
    public Convocatoria save(Convocatoria convocatoria){
        return convocatoriaRepository.save(Demanda.builder()
                .build());
    }

    public void delete(Long id){
        convocatoriaRepository.deleteById(id);
    }
*/
}