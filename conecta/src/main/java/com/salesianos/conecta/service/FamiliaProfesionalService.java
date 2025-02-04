package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.*;
import com.salesianos.conecta.error.DemandaNotFoundException;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.model.Convocatoria;
import com.salesianos.conecta.model.Demanda;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.FamiliaProfesional;
import com.salesianos.conecta.repository.DemandaRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.FamiliaProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamiliaProfesionalService {

    private final FamiliaProfesionalRepository familiaProfesionalRepository;
    private final EmpresaRepository empresaRepository;

    public List<FamiliaProfesional> findAll() {

        List<FamiliaProfesional> result = familiaProfesionalRepository.findAll();

        if (result.isEmpty()) {
            throw new FamiliaProfesionalNotFoundException();
        }

        return result;

    }

    public FamiliaProfesional findById(Long id) {
        return familiaProfesionalRepository.findById(id)
                .orElseThrow(() -> new FamiliaProfesionalNotFoundException(id));
    }

    public GetFamiliasProfesionalesDemandasDto save(CreateFamiliaprofesionalDto nueva) {
        FamiliaProfesional f = new FamiliaProfesional();
        f.setNombre(nueva.nombre());

        Set<Empresa> empresas = nueva.empresas().stream()
                .map(empresa -> empresaRepository.findById(empresa.getId())
                        .orElseThrow(() -> new EmpresaNotFoundException(empresa.getId())))
                .collect(Collectors.toSet());

        f.setEmpresas(empresas);
        familiaProfesionalRepository.save(f);

        return GetFamiliasProfesionalesDemandasDto.of(f);
    }

    public GetFamiliasProfesionalesDemandasDto edit(CreateFamiliaprofesionalDto familiaProfesional, Long id) {


        FamiliaProfesional familiaProfesionalEditar = familiaProfesionalRepository.findById(id)
                .map(old -> {
                    old.setNombre(familiaProfesional.nombre());

                    
                    return empresaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

        return GetEmpresaStringsDto.of(empresaEditar);
    }

    public void delete(Long id) {
        familiaProfesionalRepository.deleteById(id);
    }
}


