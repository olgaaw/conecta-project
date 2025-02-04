package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateConvocatoriaDto;
import com.salesianos.conecta.dto.CreateEmpresaDto;
import com.salesianos.conecta.dto.GetConvocatoriaDto;
import com.salesianos.conecta.dto.GetEmpresaStringsDto;
import com.salesianos.conecta.error.ConvocatoriaNotFoundException;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;
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

    public GetConvocatoriaDto save(CreateConvocatoriaDto nueva){

        Convocatoria c = new Convocatoria();

        c.setCursoEscolar(nueva.cursoEscolar());
        c.setNombre(nueva.nombre());


        for (Demanda d : nueva.demandas()) {
            c.adddemanda(d);
        }

        convocatoriaRepository.save(c);

        return GetConvocatoriaDto.of(c);
    }
    public GetConvocatoriaDto edit(CreateConvocatoriaDto convocatoria, Long id) {


        Convocatoria convocatoriaEditar = convocatoriaRepository.findById(id)
                .map(old -> {
                    old.setCursoEscolar(convocatoria.cursoEscolar());
                    old.setNombre(convocatoria.nombre());
                    convocatoria.demandas().forEach(old::adddemanda);
                    return convocatoriaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

        return GetConvocatoriaDto.of(convocatoriaEditar);
    }




}