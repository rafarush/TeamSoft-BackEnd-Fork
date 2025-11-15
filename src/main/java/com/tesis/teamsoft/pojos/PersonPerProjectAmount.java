package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import lombok.*;

/**
 * Plantilla para seleccionar la cantidad de personas necesarias para desarrollar cada proyecto
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonPerProjectAmount {

    private ProjectEntity proj;
    private int cant;
}
