package com.salesianos.conecta.util;

import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.ContactoRepository;
import com.salesianos.conecta.repository.EmpresaRepository;
import com.salesianos.conecta.repository.ProfesorRepository;
import com.salesianos.conecta.repository.TrabajadorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final TrabajadorRepository trabajadorRepository;
    private final ContactoRepository contactoRepository;
    private final ProfesorRepository profesorRepository;
    private final EmpresaRepository empresaRepository;

    @PostConstruct
    public void init(){


        Empresa e = Empresa.builder()
                .cif("121234123")
                .nombre("Manolo SL")
                .build();

        empresaRepository.save(e);


        Empresa e2 = Empresa.builder()
                .cif("654456456")
                .nombre("Juanes SL")
                .build();

        empresaRepository.save(e2);


        Trabajador t = Trabajador.builder()
                .nombre("pepe")
                .area("tec")
                .puesto("pro")
                .apellidos("xd")
                .email("@pepe")
                .telefono(62262626)
                .empresa(e)
                .build();

        trabajadorRepository.save(t);


        Trabajador t2 = Trabajador.builder()
                .nombre("jose")
                .area("dev")
                .puesto("noob")
                .apellidos("jeje")
                .email("@jose")
                .telefono(85858585)
                .empresa(e2)
                .build();

        trabajadorRepository.save(t2);


        Profesor p = Profesor.builder()
                .nombre("Paco")
                .apellidos("Perez")
                .email("@perez")
                .telefono(78787878)
                .build();

        profesorRepository.save(p);


        Contacto contacto = Contacto.builder()
                .contactoPK(new ContactoPK(p.getId(), t.getId()))
                .canal("Telegram")
                .resumen("sisinono")
                .fecha(new Date())
                .trabajador(t)
                .profesor(p)
                .build();

        contacto.addToProfesor(p);
        contactoRepository.save(contacto);


        Contacto contacto2 = Contacto.builder()
                .contactoPK(new ContactoPK(p.getId(), t2.getId()))
                .canal("email")
                .resumen("nounounou")
                .fecha(new Date())
                .trabajador(t2)
                .profesor(p)
                .build();

        contacto.addToProfesor(p);
        contactoRepository.save(contacto2);


    }







}

