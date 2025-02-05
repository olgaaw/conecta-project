package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.demanda.CreateDemandaDto;
import com.salesianos.conecta.dto.demanda.GetDemandaDto;
import com.salesianos.conecta.error.CursoNotFoundException;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.repository.CursoRepository;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandaService {

    private final DemandaRepository demandaRepository;
    private final EmpresaRepository empresaRepository;
    private final CursoRepository cursoRepository;

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

        d.setEmpresa(empresaRepository.findById(nueva.empresa().getId())
                .orElseThrow(() -> new EmpresaNotFoundException("Empresa no encontrada")));

        d.setCurso(cursoRepository.findById(nueva.curso().getId())
                .orElseThrow(() -> new EmpresaNotFoundException("Curso no encontrado")));

        d.setCantidadAlumnos(nueva.cantidadAlumnos());

        demandaRepository.save(d);

        return GetDemandaDto.of(d);

    }

    public GetDemandaDto edit(CreateDemandaDto demanda, Long id){

        Demanda demandaEditar = demandaRepository.findById(id)
                .map(old ->{
                    old.setEmpresa(empresaRepository.findById(demanda.empresa().getId())
                            .orElseThrow(() -> new DemandaNotFoundException(demanda.empresa().getId())));
                    old.setCurso(cursoRepository.findById(demanda.curso().getId())
                            .orElseThrow(() -> new CursoNotFoundException(demanda.curso().getId())));
                    old.setCantidadAlumnos(demanda.cantidadAlumnos());
                    return demandaRepository.save(old);
                })
                .orElseThrow(() -> new DemandaNotFoundException(id));

        return GetDemandaDto.of(demandaEditar);
    }

    public void delete(Long id){
        demandaRepository.deleteById(id);
    }

}
