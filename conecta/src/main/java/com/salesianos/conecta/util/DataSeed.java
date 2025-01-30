package com.salesianos.conecta.util;

import com.salesianos.conecta.model.*;
import com.salesianos.conecta.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final TrabajadorRepository trabajadorRepository;
    private final ContactoRepository contactoRepository;
    private final ProfesorRepository profesorRepository;
    private final EmpresaRepository empresaRepository;
    private final DemandaRepository demandaRepository;
    private final FamiliaProfesionalRepository familiaProfesionalRepository;

    @PostConstruct
    public void init(){


        Empresa e = Empresa.builder()
                .cif("121234123")
                .nombre("Manolo SL")
                .direccion("Malaga")
                .coordenadas("8557673.91")
                .build();

        empresaRepository.save(e);

        Demanda d = Demanda.builder()
                .empresa(e)
                .build();

        demandaRepository.save(d);

        Demanda d2 = Demanda.builder()
                .empresa(e)
                .build();

        demandaRepository.save(d2);

        Empresa e2 = Empresa.builder()
                .cif("654456456")
                .nombre("Juanes SL")
                .direccion("Sevilla")
                .coordenadas("34234243.88")
                .build();

        empresaRepository.save(e2);

        FamiliaProfesional f = FamiliaProfesional.builder()
                .nombre("Informática y Telecomunicaciones")
                .empresas(new HashSet<>())
                .build();

        familiaProfesionalRepository.save(f);

        e.addDemanda(d);
        e.addDemanda(d2);
        e.addFamiliaProfesional(f);
        e2.addFamiliaProfesional(f);



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

        /*
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
        contactoRepository.save(contacto2); */

        System.out.println(e);
        System.out.println(e2);
        System.out.println(f);
    }









}

