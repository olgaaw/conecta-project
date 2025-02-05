package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.convocatoria.CreateConvocatoriaDto;
import com.salesianos.conecta.error.ConvocatoriaNotFoundException;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
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
    private final DemandaRepository demandaRepository;

    public List<Convocatoria> findAll(){

        List<Convocatoria> result = convocatoriaRepository.findAll();

        if (result.isEmpty()){
            throw new ConvocatoriaNotFoundException();
        }

        return result;

    }

    public Convocatoria findById(Long id){
        return convocatoriaRepository.findById(id)
                .orElseThrow(() -> new ConvocatoriaNotFoundException(id));
    }

    public void delete(Long id){
        convocatoriaRepository.deleteById(id);
    }

    public Convocatoria save(CreateConvocatoriaDto nueva){


        Convocatoria convocatoria = Convocatoria.builder()
                .cursoEscolar(nueva.cursoEscolar())
                .nombre(nueva.nombre())
                .build();

        List<Demanda> demandas = nueva.demandas();

        for (Demanda d : demandas) {
            convocatoria.adddemanda(demandaRepository.findById(d.getId())
                    .orElseThrow(() -> new FamiliaProfesionalNotFoundException(d.getId())));
        }

        return convocatoriaRepository.save(convocatoria);
    }

    public Convocatoria edit(CreateConvocatoriaDto convocatoria, Long id) {


        return convocatoriaRepository.findById(id)
                .map(old -> {
                    old.setCursoEscolar(convocatoria.cursoEscolar());
                    old.setNombre(convocatoria.nombre());
                    List<Demanda> demandas = convocatoria.demandas().stream()
                            .map(demanda -> demandaRepository.findById(demanda.getId())
                                    .orElseThrow(() -> new DemandaNotFoundException(demanda.getId())))
                            .toList();

                    for (Demanda d : demandas) {
                        old.adddemanda(d);
                    }

                    return convocatoriaRepository.save(old);
                })
                .orElseThrow(() -> new ConvocatoriaNotFoundException(id));
        }




}