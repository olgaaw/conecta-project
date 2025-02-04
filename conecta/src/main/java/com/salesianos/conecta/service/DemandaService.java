package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateDemandaDto;
import com.salesianos.conecta.dto.GetDemandaDto;
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

    public void delete(Long id){
        demandaRepository.deleteById(id);
    }

}
