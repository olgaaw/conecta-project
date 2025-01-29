package com.salesianos.conecta.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactoPK {

    private Long profesor_id;

    private Long trabajador_id;

}
