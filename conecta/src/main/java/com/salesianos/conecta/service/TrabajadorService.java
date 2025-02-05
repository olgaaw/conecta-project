package com.salesianos.conecta.service;

import com.salesianos.conecta.dto.trabajador.CreateTrabajadorDto;
import com.salesianos.conecta.error.EmpresaNotFoundException;
import com.salesianos.conecta.error.TrabajadorNotFoundException;
import com.salesianos.conecta.model.Empresa;
import com.salesianos.conecta.model.Trabajador;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;
    private final EmpresaRepository empresaRepository;

    public List<Trabajador> findAll() {
        List<Trabajador> result = trabajadorRepository.findAll();
        if (result.isEmpty()) {
            throw new TrabajadorNotFoundException();
        }
        return result;
    }

    public Trabajador findById(Long id) {
       return trabajadorRepository.findById(id).orElseThrow(() -> new TrabajadorNotFoundException(id));
    }

    public Trabajador save(CreateTrabajadorDto dto) {
        Empresa empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new EmpresaNotFoundException(dto.empresaId()));

        return trabajadorRepository.save(dto.toTrabajador(empresa));
    }

    public Trabajador edit(CreateTrabajadorDto dto, Long id) {
        return trabajadorRepository.findById(id)
                .map(old -> {
                    old.setNombre(dto.nombre());
                    old.setApellidos(dto.apellidos());
                    old.setEmail(dto.email());
                    old.setTelefono(dto.telefono());
                    old.setArea(dto.area());
                    old.setPuesto(dto.puesto());
                    return trabajadorRepository.save(old);
                }).orElseThrow(() -> new TrabajadorNotFoundException(id));
    }

    public void delete(Long id) {
        trabajadorRepository.deleteById(id);
    }




}
