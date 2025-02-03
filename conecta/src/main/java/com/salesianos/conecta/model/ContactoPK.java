package com.salesianos.conecta.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactoPK {
    @GeneratedValue
    private Long id;

    private Long profesor_id;

    private Long trabajador_id;

}
