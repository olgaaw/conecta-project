package com.salesianos.conecta.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ContactoPK {

    private Long profesor_id;

    private Long trabajador_id;

}
