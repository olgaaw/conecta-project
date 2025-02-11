package com.salesianos.conecta.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactoPK {

    private Long profesor_id;

    private Long trabajador_id;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ContactoPK that = (ContactoPK) o;
        return getProfesor_id() != null && Objects.equals(getProfesor_id(), that.getProfesor_id())
                && getTrabajador_id() != null && Objects.equals(getTrabajador_id(), that.getTrabajador_id());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(profesor_id, trabajador_id);
    }
}
