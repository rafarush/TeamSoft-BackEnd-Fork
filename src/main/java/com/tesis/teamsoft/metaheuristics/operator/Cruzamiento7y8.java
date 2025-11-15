package com.tesis.teamsoft.metaheuristics.operator;

import com.tesis.teamsoft.persistence.entity.PersonEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Zucelys
 */
public class Cruzamiento7y8 extends TeamFormationOperator {
    public static ArrayList<ArrayList<PersonEntity>> OperadorCruzamiento7y8(ArrayList<Object> code1, ArrayList<Object> code2, int operator) throws IOException, ClassNotFoundException {
        ArrayList<PersonEntity> perSol1 = personasSolucion(code1);
        ArrayList<PersonEntity> perSol2 = personasSolucion(code2);
        ArrayList<ArrayList<PersonEntity>> result = new ArrayList<>();

        PersonEntity auxiliar;
        Random random = new Random();

        int posCruce1 = 0;
        if (operator == 7)
            posCruce1 = random.nextInt(perSol1.size());
        int posCruce2 = random.nextInt(perSol1.size());
        while (posCruce2 < posCruce1) {
            posCruce2 = random.nextInt(perSol1.size());
        }
        while (posCruce1 < posCruce2 + 1) {
            auxiliar = perSol2.get(posCruce1);
            perSol2.set(posCruce1, perSol1.get(posCruce1));
            perSol1.set(posCruce1, auxiliar);
            posCruce1++;
        }
        result.add(perSol2);
        result.add(perSol1);

        return result;
    }

}
