package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.GetEmpresaDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public List<Empresa> findAll(){

        List<Empresa> result = empresaRepository.findAll();

        if(result.isEmpty()){
            throw new EmpresaNotFoundException();
        }
        return result;
    }

    public List<GetEmpresaDto> findAllDto(){

        List<GetEmpresaDto> result = empresaRepository.GetAllDto();

        if(result.isEmpty()){
            throw new EmpresaNotFoundException();
        }
        return result;
    }

    public Empresa findById(Long id){
        return empresaRepository.findById(id)
                .orElseThrow(()-> new EmpresaNotFoundException(id));
    }

    public Empresa save(Empresa empresa){
        return empresaRepository.save(Empresa.builder()
                .cif(empresa.getCif())
                .coordenadas(empresa.getCoordenadas())
                .direccion(empresa.getDireccion())
                .nombre(empresa.getNombre())
                .build());
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

    public void delete(Long id){empresaRepository.deleteById(id);}



}
