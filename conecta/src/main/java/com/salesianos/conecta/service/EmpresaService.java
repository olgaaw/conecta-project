package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.CreateEmpresaDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return e;
    }

    public Empresa edit(Empresa empresa, Long id) {
        return empresaRepository.findById(id)
                .map(old -> {
                    old.setCif(empresa.getCif());
                    old.setCoordenadas(empresa.getCoordenadas());
                    old.setDireccion(empresa.getDireccion());
                    old.setNombre(empresa.getNombre());
                    return empresaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

    }

    public void delete(Long id){
        empresaRepository.deleteById(id);
    }



}
