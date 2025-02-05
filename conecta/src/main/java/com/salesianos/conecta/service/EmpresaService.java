package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.empresa.CreateEmpresaDto;
import com.salesianos.conecta.dto.empresa.GetEmpresaStringsDto;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final FamiliaProfesionalRepository familiaProfesionalRepository;
    private final DemandaRepository demandaRepository;

    public List<Empresa> findAll(){

        List<Empresa> result = empresaRepository.findAll();

        if(result.isEmpty()){
            throw new EmpresaNotFoundException();
        }
        return result;
    }

    public Empresa findById(Long id){
        return empresaRepository.findById(id)
                .orElseThrow(()-> new EmpresaNotFoundException(id));
    }

    public Empresa save(CreateEmpresaDto nueva){

        Empresa empresa = Empresa.builder()
                .nombre(nueva.nombre())
                .direccion(nueva.direccion())
                .build();

        Set<FamiliaProfesional> familiasProfesionales = nueva.familiasProfesionales();

        for (FamiliaProfesional f : familiasProfesionales) {
            empresa.addFamiliaProfesional(familiaProfesionalRepository.findById(f.getId())
                    .orElseThrow(() -> new FamiliaProfesionalNotFoundException(f.getId())));
        }

        List<Demanda> demandas = nueva.demandas();

        for (Demanda d : demandas) {
            empresa.addDemanda(demandaRepository.findById(d.getId())
                    .orElseThrow(() -> new FamiliaProfesionalNotFoundException(d.getId())));
        }

        return empresaRepository.save(empresa);

    }

    public Empresa edit(CreateEmpresaDto empresa, Long id) {


        return empresaRepository.findById(id)
                .map(old -> {
                    old.setNombre(empresa.nombre());
                    old.setDireccion(empresa.direccion());
                    List<Demanda> demandas = empresa.demandas().stream()
                            .map(demanda -> demandaRepository.findById(demanda.getId())
                                    .orElseThrow(() -> new DemandaNotFoundException(demanda.getId())))
                            .toList();

                    for (Demanda d : demandas) {
                        old.addDemanda(d);
                    }
                    Set<FamiliaProfesional> familiasProfesionales = empresa.familiasProfesionales().stream()
                            .map(familiaProfesional -> familiaProfesionalRepository.findById(familiaProfesional.getId())
                                    .orElseThrow(() -> new FamiliaProfesionalNotFoundException(familiaProfesional.getId())))
                            .collect(Collectors.toSet());

                    for (FamiliaProfesional f : familiasProfesionales) {
                        old.addFamiliaProfesional(f);
                    }

                    return empresaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

    }

    public void delete(Long id){

        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EmpresaNotFoundException(id));

        List<Trabajador> trabajadores = new ArrayList<>(empresa.getTrabajadores());
        for (Trabajador trabajador : trabajadores) {
            empresa.removeTrabajador(trabajador);
        }

        List<Demanda> demandas = new ArrayList<>(empresa.getDemandas());
        for (Demanda demanda : demandas) {
            empresa.removeDemanda(demanda);
        }

        Set<FamiliaProfesional> familiasProfesionales = new HashSet<>(empresa.getFamiliasProfesionales());
        for (FamiliaProfesional familiaProfesional : familiasProfesionales) {
            empresa.removeFamiliaProfesional(familiaProfesional);
        }

        empresaRepository.deleteById(id);
    }



}
