package com.tesis.teamsoft.metaheuristics.objetives;

import com.tesis.teamsoft.pojos.TeamFormationParameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import problem.definition.ObjetiveFunction;
import problem.definition.State;

import static com.tesis.teamsoft.metaheuristics.util.FactorEvaluation.AgeGroupEvaluation.ageGroupBalanceEvaluation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BalanceMaximizeAgeFactor extends ObjetiveFunction {
    private TeamFormationParameters parameters;
    public static String className = "Balancear equipos heterogéneos edad";

    @Override
    public Double Evaluation(State state) {
        return ageGroupBalanceEvaluation(state.getCode(), parameters.getSearchArea());
    }  
}

