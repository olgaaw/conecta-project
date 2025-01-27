package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contacto")
public class Contacto {
    @Id
    @GeneratedValue
    private Long id;

    private Date fecha;
    private String canal;
    private String resumen;

    @ManyToOne
    @JoinColumn(name = "profesor_id",
                foreignKey = @ForeignKey(name = "fk_profesor_contacto"))
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "trabajador_id",
                foreignKey = @ForeignKey(name = "fk_trabajador_contacto"))
    private Trabajador trabajador;



}
