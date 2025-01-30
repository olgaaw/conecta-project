package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @EmbeddedId
    private ContactoPK contactoPK = new ContactoPK();

    @ManyToOne
    @MapsId("profesor_id")
    @JoinColumn(name = "profesor_id",
            foreignKey = @ForeignKey(name = "fk_profesor_contacto"))
    private Profesor profesor;

    @ManyToOne
    @MapsId("trabajador_id")
    @JoinColumn(name = "trabajador_id",
            foreignKey = @ForeignKey(name = "fk_trabajador_contacto"))
    private Trabajador trabajador;

    private LocalDate fecha;
    private String canal;
    private String resumen;

    // Helpers

    public void addToProfesor(Profesor p) {
        p.getContactos().add(this);
        this.profesor = p;
    }

    public void removeFromProfesor(Profesor p) {
        p.getContactos().remove(this);
        this.profesor = null;
    }





}
