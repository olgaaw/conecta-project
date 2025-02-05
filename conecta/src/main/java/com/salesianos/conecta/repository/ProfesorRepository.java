package com.salesianos.conecta.repository;

import com.salesianos.conecta.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {



}
