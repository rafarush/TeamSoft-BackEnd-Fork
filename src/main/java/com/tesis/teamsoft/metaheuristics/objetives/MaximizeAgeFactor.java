package com.tesis.teamsoft.metaheuristics.objetives;

import com.tesis.teamsoft.pojos.TeamFormationParameters;
import lombok.Getter;
import lombok.Setter;
import problem.definition.ObjetiveFunction;
import problem.definition.Problem;
import problem.definition.State;

import static com.tesis.teamsoft.metaheuristics.util.FactorEvaluation.AgeGroupEvaluation.ageGroupEvaluation;

@Getter
@Setter
public class MaximizeAgeFactor extends ObjetiveFunction {

    private TeamFormationParameters parameters;
    public static String className = "Equipo Heterogéneo Edad";

    public MaximizeAgeFactor(TeamFormationParameters parameters) {
        super();
        this.parameters = parameters;
        setTypeProblem(Problem.ProblemType.Maximizar);
    }

    public MaximizeAgeFactor() {
        super();
        setTypeProblem(Problem.ProblemType.Maximizar);
    }

    @Override
    public Double Evaluation(State state) {
        return ageGroupEvaluation(state.getCode(), parameters.getSearchArea());
    }
}
