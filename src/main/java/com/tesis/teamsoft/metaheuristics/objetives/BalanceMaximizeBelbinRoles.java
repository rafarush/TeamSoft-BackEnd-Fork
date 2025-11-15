package com.tesis.teamsoft.metaheuristics.objetives;

import com.tesis.teamsoft.persistence.entity.PersonEntity;
import com.tesis.teamsoft.persistence.entity.PersonTestEntity;
import com.tesis.teamsoft.metaheuristics.util.ObjetiveFunctionUtil;
import com.tesis.teamsoft.metaheuristics.util.ProjectRole;
import com.tesis.teamsoft.metaheuristics.util.RoleWorker;
import problem.definition.ObjetiveFunction;
import problem.definition.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G1lb3rt
 */
public class BalanceMaximizeBelbinRoles extends ObjetiveFunction {

    public static String className = "BalanceRolesBelbin";

    @Override
    public Double Evaluation(State state) {

        List<Object> projects = state.getCode(); // obtener lista de proyectos -roles
        List<Double> projectSum = new ArrayList<>();
        List<Double> projectSumMax = new ArrayList<>();
        double belbinProject_max;
        double sum = 0;
        double belbinProject;


        int amount_projects_max;
        if(projects.size()%2 == 0){ //Si la cantidad de proyectos es par
            amount_projects_max = projects.size()/2;
        }
        else{
            amount_projects_max = (projects.size()+1)/2;
        }

        int i = 0;
        while (i < projects.size()) { //para cada projecto-rol
            ProjectRole projectRole = (ProjectRole) projects.get(i);
            List<RoleWorker> roleWorkers = projectRole.getRoleWorkers();
            belbinProject = 0;

            List<PersonEntity> team = new ArrayList<>(); //listado de personas del projecto

            int j = 0;
            while (j < roleWorkers.size()) { //para cada rol-persona
                RoleWorker roleWorker = roleWorkers.get(j);
                team.addAll(roleWorker.getFixedWorkers());  // concatenar listas de personas y personas fijadas por el usuario
                team.addAll(roleWorker.getWorkers()); //añadir personas que juegan el rol actual a la lista de personas del proyecto

                j++;
            }

            int CE = 0;
            int ME = 0;
            int ES = 0;
            int ID = 0;
            int IS = 0;
            int IF = 0; //(el rol real de elbin es FI no IF)
            int CO = 0;
            int CH = 0;
            int IR = 0;

            int k = 0;
            while (k < team.size()) {  //para cada persona del equipo de proyecto actual
                PersonEntity worker = team.get(k);
                PersonTestEntity workerTest = worker.getPersonTest(); //obtener caracteristicas psicologicas

                if (workerTest != null) {
                    if (workerTest.getI_D() != 'I' && workerTest.getI_D() != 'E' && ID != 1) {
                        ID = 1;
                    }
                    if (workerTest.getI_S() != 'I' && workerTest.getI_S() != 'E' && IS != 1) {
                        IS = 1;
                    }
                    if (workerTest.getI_F() != 'I' && workerTest.getI_F() != 'E' && IF != 1) {
                        IF = 1;
                    }
                    if (workerTest.getC_E() != 'I' && workerTest.getC_E() != 'E' && CE != 1) {
                        CE = 1;
                    }
                    if (workerTest.getM_E() != 'I' && workerTest.getM_E() != 'E' && ME != 1) {
                        ME = 1;
                    }
                    if (workerTest.getE_S() != 'I' && workerTest.getE_S() != 'E' && ES != 1) {
                        ES = 1;
                    }
                    if (workerTest.getC_O() != 'I' && workerTest.getC_O() != 'E' && CO != 1) {
                        CO = 1;
                    }
                    if (workerTest.getC_H() != 'I' && workerTest.getC_H() != 'E' && CH != 1) {
                        CH = 1;
                    }
                    if (workerTest.getI_R() != 'I' && workerTest.getI_R() != 'E' && IR != 1) {
                        IR = 1;
                    }

                    belbinProject = ID + IS + IF + CE + ME + ES + CO + CH + IR;
                }
                k++;
            }
            sum += belbinProject/9;
            projectSum.add(belbinProject/9); //registrar el valor de la FO sin balancear por cada equipo

            if(amount_projects_max > 0){
                projectSumMax.add(1d); //La razón de máximo interes es 1, indicando que todos tienen preferencia por los roles asignados
                amount_projects_max--;
            }
            else{
                projectSumMax.add(0d); //La razón de mínimo interés es 0, indicando que nadie tiene preferencia por los roles asignados
            }
            i++;


        }
        sum = ObjetiveFunctionUtil.balance(projectSum);

        //Calcular el máximo valor de balance
        belbinProject_max = ObjetiveFunctionUtil.balance(projectSumMax);

        sum = sum / belbinProject_max;// normalizar resultado (valor entre 0-1)
        return sum;
    }


}
