package com.tesis.teamsoft.metaheuristics.operator;

import com.tesis.teamsoft.persistence.entity.PersonEntity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Zucelys
 */
public class Cruzamiento4 extends TeamFormationOperator {

    public static PersonEntity OperadorCruzamiento4(ArrayList<PersonEntity> pers1, ArrayList<PersonEntity> pers2, Long idRol, int numVect)
            throws IOException, ClassNotFoundException {
        PersonEntity temPersona;
        ArrayList<PersonEntity> personas = pers1;
        if (numVect == 1)
            personas = pers2;
        temPersona = personaConMayorExperienciaParaRolSolucion(idRol, personas);
        return temPersona;
    }

}
