package com.tesis.teamsoft.metaheuristics.operator;

import com.tesis.teamsoft.persistence.entity.PersonEntity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Zucelys
 */
public class Cruzamiento5 extends TeamFormationOperator {

    public static PersonEntity OperadorCruzamiento5(ArrayList<PersonEntity> pers1, ArrayList<PersonEntity> pers2, Long idRol, ArrayList<PersonEntity> persProy, int numVect)
            throws IOException, ClassNotFoundException {
        PersonEntity temPersona;
        ArrayList<PersonEntity> personas = pers1;
        if (numVect == 1)
            personas = pers2;
        if (persProy.isEmpty())
            temPersona = personaConMayorExperienciaParaRolSolucion(idRol, personas);
        else
            temPersona = personaMayorIdoneidadParaSolucion(persProy, idRol, personas);

        return temPersona;
    }

}
