package com.tesis.teamsoft.metaheuristics.operator;

import com.tesis.teamsoft.persistence.entity.PersonEntity;

import java.io.IOException;
import java.util.ArrayList;

public class Cruzamiento6 extends TeamFormationOperator {
    public static PersonEntity OperadorCruzamiento6(PersonEntity pers1, PersonEntity pers2, Long idRol, ArrayList<PersonEntity> persProy)
            throws IOException, ClassNotFoundException {
        ArrayList<PersonEntity> personas = new ArrayList<>();
        personas.add(pers1);
        personas.add(pers2);
        PersonEntity temPersona;
        if (persProy.isEmpty())
            temPersona = personaConMayorExperienciaParaRolSolucion(idRol, personas);
        else
            temPersona = personaMayorIdoneidadParaSolucion(persProy, idRol, personas);

        return temPersona;
    }

}
