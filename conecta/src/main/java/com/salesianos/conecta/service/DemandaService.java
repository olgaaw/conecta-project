package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateDemandaDto;
import com.salesianos.conecta.dto.GetDemandaDto;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.repository.DemandaRepository;
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
            throw new DemandaNotFoundException();
        }

        return result;

    }

    public Demanda findById(Long id){
        return demandaRepository.findById(id)
                .orElseThrow(() -> new DemandaNotFoundException(id));
    }

    public GetDemandaDto save(CreateDemandaDto nueva){

        Demanda d = new Demanda();

        d.setEmpresa(nueva.empresa());
        d.setCurso(nueva.curso());
        d.setCantidadAlumnos(nueva.cantidadAlumnos());

        demandaRepository.save(d);

        return GetDemandaDto.of(d);

    }

    public void delete(Long id){
        demandaRepository.deleteById(id);
    }

}
