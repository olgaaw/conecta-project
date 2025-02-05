package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.convocatoria.CreateConvocatoriaDto;
import com.salesianos.conecta.dto.convocatoria.GetConvocatoriaDto;
import com.salesianos.conecta.error.ConvocatoriaNotFoundException;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.repository.ConvocatoriaRepository;
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
                .demandas(nueva.demandas())
                .build();

        return convocatoriaRepository.save(convocatoria);
    }

    public Convocatoria edit(CreateConvocatoriaDto convocatoria, Long id) {


        return convocatoriaRepository.findById(id)
                .map(old -> {
                    old.setCursoEscolar(convocatoria.cursoEscolar());
                    old.setNombre(convocatoria.nombre());
                    convocatoria.demandas().forEach(old::adddemanda);
                    return convocatoriaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));
        }




}