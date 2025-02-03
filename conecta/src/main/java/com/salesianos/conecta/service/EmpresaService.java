package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateEmpresaDto;
import com.salesianos.conecta.dto.GetEmpresaStringsDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Empresa e = new Empresa();

        e.setNombre(nueva.nombre());
        e.setDireccion(nueva.direccion());

        for (FamiliaProfesional f : nueva.familiasProfesionales()){
            e.addFamiliaProfesional(f);
        }

        for (Demanda d : nueva.demandas()){
            e.addDemanda(d);
        }

        empresaRepository.save(e);

        return e;
    }

    public GetEmpresaStringsDto edit(CreateEmpresaDto empresa, Long id) {


        Empresa empresaEditar = empresaRepository.findById(id)
                .map(old -> {
                    old.setNombre(empresa.nombre());
                    old.setDireccion(empresa.direccion());
                    empresa.familiasProfesionales().forEach(old::addFamiliaProfesional);
                    empresa.demandas().forEach(old::addDemanda);
                    return empresaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

        return GetEmpresaStringsDto.of(empresaEditar);
    }

    public void delete(Long id){empresaRepository.deleteById(id);}



}
