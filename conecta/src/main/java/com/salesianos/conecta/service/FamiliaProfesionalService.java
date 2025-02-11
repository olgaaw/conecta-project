package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.familiaProfesional.CreateFamiliaprofesionalDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.FamiliaProfesionalNotFoundException;
import com.salesianos.conecta.model.*;
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

    public FamiliaProfesional save(CreateFamiliaprofesionalDto nueva) {

        FamiliaProfesional familiaProfesional = new FamiliaProfesional();
        familiaProfesional.setNombre(nueva.nombre());

        Set<Empresa> empresas = nueva.empresas().stream()
                .map(empresa -> empresaRepository.findById(empresa.getId())
                        .orElseThrow(() -> new EmpresaNotFoundException(empresa.getId())))
                .collect(Collectors.toSet());


        familiaProfesional.setEmpresas(empresas);

        return familiaProfesionalRepository.save(familiaProfesional);

    }

    public FamiliaProfesional edit(CreateFamiliaprofesionalDto familiaProfesional, Long id) {


        return familiaProfesionalRepository.findById(id)
                .map(old -> {
                    old.setNombre(familiaProfesional.nombre());

                    Set<Empresa> nuevasEmpresas = familiaProfesional.empresas().stream()
                            .map(empresa -> empresaRepository.findById(empresa.getId())
                                    .orElseThrow(() -> new EmpresaNotFoundException(empresa.getId())))
                            .collect(Collectors.toSet());

                    old.setEmpresas(nuevasEmpresas);

                    return familiaProfesionalRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException(id));

    }

    public void delete(Long id) {

        FamiliaProfesional familiaProfesional = familiaProfesionalRepository.findById(id)
                .orElseThrow(() -> new FamiliaProfesionalNotFoundException(id));

        Set<Empresa> empresas = new HashSet<>(familiaProfesional.getEmpresas());
        for (Empresa empresa : empresas) {
            empresa.removeFamiliaProfesional(familiaProfesional);
        }

        List<Titulo> titulos = new ArrayList<>(familiaProfesional.getTitulos());
        for (Titulo titulo : titulos) {
            familiaProfesional.removeTitulo(titulo);
        }

        familiaProfesionalRepository.delete(familiaProfesional);
    }

}


