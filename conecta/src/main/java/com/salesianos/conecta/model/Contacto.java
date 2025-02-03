package com.salesianos.conecta.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contacto")
@SQLDelete(sql = "UPDATE contacto SET deleted = true WHERE id=?")
@FilterDef(name = "deletedContactoFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedContactoFilter", condition = "deleted = :isDeleted")
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

    private Date fecha;
    private String canal;
    private String resumen;
    private Boolean deleted = false;

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
