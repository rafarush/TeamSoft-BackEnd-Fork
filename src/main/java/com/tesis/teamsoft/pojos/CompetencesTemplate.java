package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.CompetenceEntity;
import com.tesis.teamsoft.persistence.entity.CompetenceImportanceEntity;
import com.tesis.teamsoft.persistence.entity.LevelsEntity;
import lombok.*;

/**
 * Esta Clase sirve de plantilla para los datos que se le pasan a la tabla de
 * competencias tecnicaas y genericas en la vista Seleccionar Jefe de Proyecto
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetencesTemplate {

    private CompetenceEntity competence;
    private LevelsEntity minLevel;
    private CompetenceImportanceEntity competenceImportance;
}
