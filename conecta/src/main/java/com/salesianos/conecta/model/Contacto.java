package com.salesianos.conecta.model;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("profesor_id")
    @JoinColumn(name = "profesor_id",foreignKey = @ForeignKey(name = "fk_profesor_contacto"))
    private Profesor profesor;

    @ManyToOne
    @MapsId("trabajador_id")
    @JoinColumn(name = "trabajador_id",
            foreignKey = @ForeignKey(name = "fk_trabajador_contacto"))
    private Trabajador trabajador;

    private LocalDate fecha;
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


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Contacto contacto = (Contacto) o;
        return getContactoPK() != null && Objects.equals(getContactoPK(), contacto.getContactoPK());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(contactoPK);
    }
}
