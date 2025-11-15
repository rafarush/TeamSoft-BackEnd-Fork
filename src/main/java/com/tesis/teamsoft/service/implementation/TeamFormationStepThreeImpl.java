package com.tesis.teamsoft.service.implementation;

import com.tesis.teamsoft.presentation.dto.DefaultTreeNode;
import com.tesis.teamsoft.presentation.dto.TreeNode;
import com.tesis.teamsoft.service.interfaces.ITeamFormationStepThreeService;
import evolutionary_algorithms.complement.CrossoverType;
import evolutionary_algorithms.complement.MutationType;
import evolutionary_algorithms.complement.ReplaceType;
import evolutionary_algorithms.complement.SelectionType;
import local_search.complement.StopExecute;
import local_search.complement.TabuSolutions;
import local_search.complement.UpdateParameter;
import metaheurictics.strategy.Strategy;
import metaheuristics.generators.*;
import com.tesis.teamsoft.pojos.*;
import com.tesis.teamsoft.persistence.entity.*;
import com.tesis.teamsoft.metaheuristics.operator.TeamBuilder;
import com.tesis.teamsoft.metaheuristics.operator.TeamFormationOperator;
import com.tesis.teamsoft.metaheuristics.restrictions.*;
import com.tesis.teamsoft.metaheuristics.util.*;
import problem.definition.ObjetiveFunction;
import problem.definition.Problem;
import problem.definition.State;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamFormationStepThreeImpl implements ITeamFormationStepThreeService {

    /**
     * Construir equipos (mejorando solución con la aplicacón metaheurísticas)
     */
    public TreeNode getTeam(TeamFormationParameters parameters) throws Exception {

        ProjectRoleState initialVoidSolution = TeamBuilder.getInitialVoidSolution(parameters); // construir una solucion inicial vacía (contendrá las personas que ya fueron asignadas a cada rol)

        TreeNode teamProposal = new TreeNode();

        //Funciones objetivo
        ArrayList<ObjetiveFunction> objectiveFunctions = new ArrayList<>();
        objectiveFunctions.addAll(ObjetiveFunctionUtil.getObjectiveFunctions(parameters));
        //Restricciones
        ArrayList<Constrain> restrictions = new ArrayList<>();
        restrictions.addAll(getRestrictions(parameters));
        //Operador
        TeamFormationOperator operator = new TeamFormationOperator();
        //Problema
        TeamFormationProblem problem = new TeamFormationProblem();

        problem.setOperator(operator);
        problem.setState(initialVoidSolution);
        problem.setTypeSolutionMethod(parameters.getSolutionMethodOptionTeam());
        problem.setTypeProblem(Problem.ProblemType.Maximizar); //Cambiado de Maximizar a Minimizar
        problem.setFunction(objectiveFunctions);
        problem.setParameters(parameters);

        ArrayList<PersonEntity> searchArea = parameters.getSearchArea();
        TeamFormationCodification codification = new TeamFormationCodification(restrictions, problem, searchArea); //Inicializar parametros de la codificacion

        problem.setState(initialVoidSolution);
        problem.setCodification(codification);

        switch (parameters.getSolutionMethodOptionTeam()) { //Seleccion de algoritmo

            case FactoresPonderados: {
                if (ensureGeneralFactorWeightSummatory(parameters)) {

                    switch (parameters.getSolutionAlgorithm()) {
                        case 1:
                            teamProposal = buildTeamProposalTree(applyHillClimbing(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 2:
                            teamProposal = buildTeamProposalTree(applyHillClimbingRestart(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 3:
                            teamProposal = buildTeamProposalTree(applyRandomSearch(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 4:
                            teamProposal = buildTeamProposalTree(applyTabuSearch(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 5:
                            teamProposal = buildTeamProposalTree(applyAlgorithmsBriefcase(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 6:
                            teamProposal = buildTeamProposalTree(GA(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                        case 7:
                            teamProposal = buildTeamProposalTree(applySimulatedAnnealing(initialVoidSolution, problem), parameters);
                            if (teamProposal.getChildCount() < 1) {
                                throw new IllegalArgumentException("impossible_get_proposal");
                            }
                            break;
                    }
                } else {
                    throw new IllegalArgumentException("fo_weights_most_sum_one");
                }
            }
            break;

            case MultiObjetivoPuro: {

                switch (parameters.getSolutionAlgorithm()) {

                    case 1:
                        teamProposal = buildTeamProposalTree(applyMultiobjectiveStochasticHC(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;

                    case 2:
                        teamProposal = buildTeamProposalTree(applyMultiobjectiveHCRestart(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;

                    case 3:
                        teamProposal = buildTeamProposalTree(applyMultiobjectiveHCDistance(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;

                    case 4:
                        teamProposal = buildTeamProposalTree(applyMultiobjectiveTabuSearch(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;

                    case 5:
                        teamProposal = buildTeamProposalTree(applyMultiobjectiveAlgorithmsBriefcase(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;
                    case 6:
                        teamProposal = buildTeamProposalTree(applyMOGA(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;
                    case 7:
                        teamProposal = buildTeamProposalTree(applyUMOSA(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;
                    case 8:
                        teamProposal = buildTeamProposalTree(applyMCMOSA(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;
                    case 9:
                        teamProposal = buildTeamProposalTree(applyNSGAII(initialVoidSolution, problem), parameters);
                        if (teamProposal.getChildCount() < 1) {
                            throw new IllegalArgumentException("impossible_get_proposal");
                        }
                        break;
                }
            }
            break;
        }

        return teamProposal;
    }

    public TreeNode buildTeamProposalTree(List<State> teamProposal, TeamFormationParameters parameters) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        TreeNode root = TreeNode.createRootNode(); // En lugar de new DefaultTreeNode()

        if (!teamProposal.isEmpty()) {
            String format = ResourceBundle.getBundle("/algorithmConf").getString("decimalFormat");
            DecimalFormat df = new DecimalFormat(format);
            root.setExpanded(true);

            TreeNode prop;
            TreeNode projectNode;
            TreeNode projectEvaluation;
            TreeNode roleNode;
            TreeNode personNode;

            ArrayList<ObjetiveFunction> objectiveFunctions = new ArrayList<>(ObjetiveFunctionUtil.getObjectiveFunctions(parameters));

            for (State state : teamProposal) {
                if (state != null) {
                    StringBuilder eval = new StringBuilder();
                    // ... (el resto del código permanece igual)

                    String formattedEval = "Propuesta: " + eval.toString();
                    prop = new DefaultTreeNode(formattedEval); // Usa tu DefaultTreeNode
                    prop.setType("Prop");
                    prop.setExpanded(false);
                    root.addChild(prop); // En lugar de root.getChildren().add(prop)

                    List<Object> projects = state.getCode();

                    if (projects != null) {
                        for (Object proj : projects) {
                            ProjectRole projectRole = (ProjectRole) proj;

                            projectNode = new DefaultTreeNode(projectRole.getProject());
                            projectNode.setType("P");
                            projectNode.setSelectable(false);
                            projectNode.setExpanded(false);
                            prop.addChild(projectNode); // En lugar de prop.getChildren().add(projectNode)

                            // ... (el resto del código similar, usando addChild() en lugar de getChildren().add())
                        }
                    }
                }
            }
        }
        return root;
    }

    public boolean ensureGeneralFactorWeightSummatory(TeamFormationParameters parameters) {
//        boolean isOk = true;
//        float weight = 0;
//
//        if (parameters.isMaxCompetences()) {
//            weight +=  competenceWeight;
//        }
//        if (parameters.isTakeWorkLoad()) {
//            weight += workLoadWeight;
//        }
//        if (parameters.isMinCostDistance()) {
//            weight += costDistanceWeight;
//        }
//        if (parameters.isMaxInterests()) {
//            weight += interestWeight;
//        }
//        if (takeProjectInterests) {
//            weight += projectInterestWeight;
//        }
//        if (takeSynergy) {
//            weight += synergyWeight;
//        }
//        if (takeBelbin) {
//            weight += belbinWeight;
//        }
//        if (takeMbtiTypes) {
//            weight += mbtiWeight;
//        }
//        if (takeMultiroleTeamMembers) {
//            weight += multiRoleTeamMembersWeight;
//        }
//        if (homogeneousTeams) {
//            weight += homogeneousTeamsWeight;
//        }
//        if (heterogeneousTeams) {
//            weight += heterogeneousTeamsWeight;
//        }
//        if (takeHomogeneousSex) {
//            weight += homogeneousSexWeight;
//        }
//        if (takeHeterogeneusSex) {
//            weight += heterogeneusSexWeight;
//        }
//        if (balanceCompetences) {
//            weight += balanceCompetenceWeight;
//        }
//        if (balanceWorkLoad) {
//            weight += balanceWorkLoadWeight;
//        }
//        if (balanceCostDistance) {
//            weight += balanceCostDistanceWeight;
//        }
//        if (balanceInterests) {
//            weight += balanceInterestWeight;
//        }
//        if (balanceProjectInterests) {
//            weight += balanceProjectInterestWeight;
//        }
//        if (balanceMbtiTypes) {
//            weight += balanceMbtiTypesWeight;
//        }
//        if (balanceSynergy) {
//            weight += balanceSynergyWeight;
//        }
//        if (balanceBelbin) {
//            weight += balanceBelbinWeight;
//        }
//        if (balanceMultiroleTeamMembers) {
//            weight += balanceMultiRoleTeamMembersWeight;
//        }
//        if (balanceMaximizeSexFactor) {
//            weight += balanceMaximizeSexFactorWeight;
//        }
//        if (balanceMinimizeSexFactor) {
//            weight += balanceMinimizeSexFactorWeight;
//        }
//        if (balanceHeterogeneousTeamsNacionalityFactor) {
//            weight += balanceHeterogeneousTeamsNacionalityFactorWeight;
//        }
//        if (balanceHomogeneousTeamsNacionalityFactor) {
//            weight += balanceHomogeneousTeamsNacionalityFactorWeight;
//        }
//        if (takeReligionHeterogeneous) {
//            weight += religionHeterogeneousWeight;
//        }
//        if (takeReligionHomogeneous) {
//            weight += religionHomogeneousWeight;
//        }
//        if (balanceReligionHeterogeneous) {
//            weight += balanceReligionHeterogeneousWeight;
//        }
//        if (balanceReligionHomogeneous) {
//            weight += balanceReligionHomogeneousWeight;
//        }
//        if (takeAgeHeterogeneity) {
//            weight += ageHeterogeneityWeight;
//        }
//        if (takeAgeHomogeneity) {
//            weight += ageHomogeneityWeight;
//        }
//        if (balanceAgeHeterogeneity) {
//            weight += balanceAgeHeterogeneityWeight;
//        }
//        if (balanceAgeHomogeneity) {
//            weight += balanceAgeHomogeneityWeight;
//        }
//
//        if (weight != 1) {
//            isOk = false;
//        }
//        return isOk;

        return true;
    }

    public List getRestrictions(TeamFormationParameters parameters) {
        List restrictions = new ArrayList();

        if (parameters != null) {
            restrictions.add(new WorkerNotRepeatedInSameRole());//obligatoria, evitar que se repita la misma persona en un rol
            restrictions.add(new WorkerRemovedFromRole()); //obligatoria, que la persona no este asignada a un ciclo anterior activo del proyecto
            if (parameters.isConfRole()) {
                restrictions.add(new MaximumRoles()); //Que una persona no pueda desempeÃ±ar mÃ¡s roles que los definidos en la interfaz
            }
            if (parameters.isOnlyOneProject()) { //una persona solo puede ser asignada a un proyecto
                restrictions.add(new DifferentPersonByProject());
            }
            if (parameters.isCanBeProjectBoss()) { //una persona solo puede ser asignada a un proyecto
                restrictions.add(new IsWorkerAssigned());
            }
            if (parameters.isCanBeProjectBoss()) { //una persona solo puede ser jefe de un projecto
                restrictions.add(new IsProjectBoss());
            }
            if (parameters.isConfAllGroup()) { //una persona solo puede ser jefe de un projecto
                restrictions.add(new PersonPerGroupAssigned());
            }
            if (parameters.isTakeCustomPersonWorkLoad()) { //la persona no puede tener una carga de trabajo superior a la definida
                if (parameters.getMaxRoleLoad() != null) {
                    restrictions.add(new MaxWorkload(parameters));
                }
            }
            if (parameters.isMaxCompetencesByProject()) { // cada persona debe tener los niveles de competencias seleccionados
                restrictions.add(new AllCompetitionLevels(parameters));
            }
            if (parameters.isAnyIncompatibility()) {
                restrictions.add(new IncompatibleWorkers());
            }
            if (parameters.isAnySelectedIncompatibility()) {
                if (parameters.getSWI() != null && !parameters.getSWI().isEmpty()) {
                    restrictions.add(new IncompatibleSelectedWorkers(parameters));
                }
                // incompatibilidades de la visual ede conf equipo
                if (parameters.getSRI() != null && !parameters.getSRI().isEmpty()) {
                    restrictions.add(new IncompatibleRoles(parameters));
                }
            }
            //sobre las caracteristicas psicologicas considerar
            if (parameters.isAllBelbinRoles()) { //exigir presencia de todos los roles de belbin
                restrictions.add(new AllBelbinRoles());
            }
            if (parameters.isDemandNBrains()) { //demndar una cantidad de personas con rol cerebro por equipo
                restrictions.add(new ExistCerebro(parameters));
            }
            if (parameters.isBalanceBelbinCategories()) { //balancear categorias de belbin
                restrictions.add(new isBalanced(parameters));
            }
            if (parameters.isAllBelbinCategories()) { //exigir precensia de todas las categorias de roles de belbin
                restrictions.add(new AllBelbinCategories());
            }
            if (parameters.isMinimunRoles()) {
                restrictions.add(new MinimumRoles());
            }
            if (parameters.isBossNeedToBeAssignedToAnotherRoles()) {
                restrictions.add(new BossNeedToBeAssignedToAnotherRole());
            }
        }
        return restrictions;
    }

    public void configStrategy(TeamFormationProblem problem) {
        // Configurando elementos comunes de la estrategia
        Strategy.getStrategy().setStopexecute(new StopExecute());
        Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
        Strategy.getStrategy().setProblem(problem);
        Strategy.getStrategy().calculateTime = Boolean.parseBoolean(ResourceBundle.getBundle("/algorithmConf").getString("calculateTime"));
    }

    public State prepareSolution() {
        List<State> states = Strategy.getStrategy().listStates;
        State bestState = null;
        int i = 0;
        boolean found = false;

        while (i < states.size() && !found) {
            if (Strategy.getStrategy().getProblem().getCodification().validState(states.get(i))) {
                found = true;
                bestState = states.get(i);
            }
            i++;
        }

        while (i < states.size()) {
            if (Strategy.getStrategy().getProblem().getCodification().validState(states.get(i))) {
                if (states.get(i).getEvaluation().get(0) > bestState.getEvaluation().get(0)) {
                    bestState = states.get(i);
                }
            }
            i++;
        }
        return bestState;
    }

    /**
     * Mejorar solucion aplicando el metodo HillClimbing
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyHillClimbing(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {

                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;

                Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.HillClimbing);
                State bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                    //SaveTxt.writeStatesMono();
                    long time = System.currentTimeMillis() / 1000;
                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }
        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el metodo HillClimbing
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyHillClimbingRestart(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                HillClimbingRestart.count = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("HillClimbingRestartCount"));

                Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.HillClimbingRestart);
                State bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                    //SaveTxt.writeStatesMono();

                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                        //SaveTxt.writeStatesMono();
                    }
                }
                Strategy.destroyExecute();
            }

        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el metodo RandomSearch
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyRandomSearch(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;

                Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.RandomSearch);
                State bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }

        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el metodo TabuSearch
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyTabuSearch(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                TabuSolutions.maxelements = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("TabuSolutionsMaxelements"));

                Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.TabuSearch);
                State bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }

        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el portafolio de algoritmos
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyAlgorithmsBriefcase(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, SecurityException, InstantiationException, InvocationTargetException {

        List<State> sol = null;
        if (initialSolution != null) {

            int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
            int executions = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
            for (int i = 0; i < executions; i++) {
                //RS,RE,RL,EE,GA,BT,BA,ECR,EDA,EC,LU
                configurePA(iterations, false, false, false, false, false, true, true, true, false, true, false);
                Strategy.getStrategy().setStopexecute(new StopExecute());
                Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
                Strategy.getStrategy().setProblem(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().calculateTime = true;
                Strategy.getStrategy().saveListStates = true;
                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiGenerator);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            Strategy.destroyExecute();
        }
        return sol;
    }
    //NSGAII

    private List<State> applyNSGAII(ProjectRoleState initialSolution, TeamFormationProblem problem) {
        int ITERATIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();
            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                //Configurar el algoritmo dentro del BiCIAM
                Strategy.getStrategy().setStopexecute(new StopExecute());
                Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
                NSGAII.countRef = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("CountRef")); //Cantidad de individuos
                NSGAII.selectionType = SelectionType.TournamentSelection;

                NSGAII.crossoverType = CrossoverType.GenericCrossover;
                NSGAII.mutationType = MutationType.GenericMutation;
                Strategy.getStrategy().calculateTime = true;
                NSGAII.PM = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PM"));
                NSGAII.PC = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PC"));

                try {
                    Strategy.getStrategy().executeStrategy(ITERATIONS, 1, GeneratorType.RandomSearch);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }
        }
        return sol;
    }

    //MCMOSA
    public List<State> applyMCMOSA(ProjectRoleState initialSolution, TeamFormationProblem problem) {

        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;
                MultiCaseSimulatedAnnealing.countIterationsT = 100;
                MultiCaseSimulatedAnnealing.alpha = 0.9;
                MultiCaseSimulatedAnnealing.tfinal = 0.0;
                MultiCaseSimulatedAnnealing.tinitial = 20.0;
                String format = ResourceBundle.getBundle("/algorithmConf").getString("decimalFormat");
                DecimalFormat df = new DecimalFormat(format);

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiCaseSimulatedAnnealing);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();

            }
        }
        return sol;
    }

    //UMOSA
    public List<State> applyUMOSA(ProjectRoleState initialSolution, TeamFormationProblem problem) {

        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();
            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;
                UMOSA.countIterationsT = 100;
                UMOSA.alpha = 0.9;
                UMOSA.tfinal = 0.0;
                UMOSA.tinitial = 20.0;
                String format = ResourceBundle.getBundle("/algorithmConf").getString("decimalFormat");
                DecimalFormat df = new DecimalFormat(format);

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.UMOSA);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }
                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();

            }
        }
        return sol;
    }
    //*Mogaaa

    public List<State> applyMOGA(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, NoSuchFieldException {
        int ITERATIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();
            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                //Configurar el algoritmo dentro del BiCIAM
                Strategy.getStrategy().setStopexecute(new StopExecute());
                Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
                MOGA.countRef = 100;//Integer.valueOf(ResourceBundle.getBundle("/algorithmConf").getString("CountRef")); //Cantidad de individuos
                MOGA.selectionType = SelectionType.TournamentSelection;
                MOGA.crossoverType = CrossoverType.GenericCrossover;
                MOGA.mutationType = MutationType.GenericMutation;
                MOGA.replaceType = ReplaceType.GenerationalReplace;
                Strategy.getStrategy().calculateTime = true;
                MOGA.PM = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PM"));
                MOGA.PC = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PC"));
                try {
                    Strategy.getStrategy().executeStrategy(ITERATIONS, 1, GeneratorType.RandomSearch);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }
                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }
        }
        return sol;

    }

    public List<State> applySimulatedAnnealing(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, NoSuchFieldException {

        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;

                SimulatedAnnealing.alpha = 0.9;
                SimulatedAnnealing.tinitial = 20.0;
                SimulatedAnnealing.tfinal = 0.0;
                SimulatedAnnealing.countIterationsT = 100;
                SimulatedAnnealing.saveRestart = true;
                Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.SimulatedAnnealing);

                State bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }

        }
        return sol;
    }

    public void configurePA(int countMaxIterations, boolean RS, boolean RE, boolean RL, boolean EE, boolean GA, boolean BT, boolean BA, boolean ECR, boolean EDA, boolean EC, boolean LU) throws IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, SecurityException, InstantiationException, InvocationTargetException {
        Strategy.getStrategy().initializeGenerators();
        List<Generator> gene = new ArrayList<>();
        if (RS) {
            SimulatedAnnealing sa = new SimulatedAnnealing();
            SimulatedAnnealing.alpha = 0.9;
            SimulatedAnnealing.tinitial = 20.0;
            SimulatedAnnealing.tfinal = 0.0;
            SimulatedAnnealing.countIterationsT = 100;
            SimulatedAnnealing.saveRestart = true;
            gene.add(sa);
        }
        if (RE) {
            double chi = 0.75;
            double omega1 = -2462.822;
            double tempInitial1 = omega1 / Math.log(chi);
            SimulatedAnnealingExponential1 rs = new SimulatedAnnealingExponential1();
            SimulatedAnnealingExponential1.tinitial = tempInitial1;
            SimulatedAnnealingExponential1.tfinal = 0.0;
            double A1 = (tempInitial1 - rs.tfinal) * (10000 + 1) / 10000;
            double B1 = tempInitial1 - A1;
            SimulatedAnnealingExponential1.valueA = A1;
            SimulatedAnnealingExponential1.valueB = B1;
            SimulatedAnnealingExponential1.countIterationsT = 50;
            gene.add(rs);
        }
        if (RL) {
            double chi = 0.75;
            double omega1 = -2462.822;
            double tempInitial1 = omega1 / Math.log(chi);
            SimulatedAnnealingLinear rl = new SimulatedAnnealingLinear();
            SimulatedAnnealingLinear.t0 = tempInitial1;
            SimulatedAnnealingLinear.tinitial = tempInitial1;
            SimulatedAnnealingLinear.countIterationsT = 50;
            gene.add(rl);
        }
        if (EE) {
            EvolutionStrategies ee = new EvolutionStrategies();
            EvolutionStrategies.countRef = 10;
            EvolutionStrategies.PM = 0.9;
            EvolutionStrategies.selectionType = SelectionType.TruncationSelection;
            EvolutionStrategies.mutationType = MutationType.GenericMutation;
            EvolutionStrategies.replaceType = ReplaceType.GenerationalReplace;
            EvolutionStrategies.truncation = 5;
            gene.add(ee);
        }
        if (GA) {
            GeneticAlgorithm ag = new GeneticAlgorithm();
            GeneticAlgorithm.countRef = 10;
            GeneticAlgorithm.PC = 0.5;
            GeneticAlgorithm.PM = 0.9;
            GeneticAlgorithm.selectionType = SelectionType.TournamentSelection;
            GeneticAlgorithm.crossoverType = CrossoverType.GenericCrossover;
            GeneticAlgorithm.mutationType = MutationType.GenericMutation;
            GeneticAlgorithm.replaceType = ReplaceType.SteadyStateReplace;
            GeneticAlgorithm.truncation = 5;
            gene.add(ag);
        }
        if (BT) {
            TabuSearch ts = new TabuSearch();
            TabuSolutions.maxelements = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("TabuSolutionsMaxelements"));
            gene.add(ts);
        }
        if (EC) {
            HillClimbing hc = new HillClimbing();
            gene.add(hc);
        }
        if (BA) {
            RandomSearch rs = new RandomSearch();
            gene.add(rs);
        }
        if (ECR) {
            HillClimbingRestart hcr = new HillClimbingRestart();
            hcr.count = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("HillClimbingRestartCount"));
            gene.add(hcr);
        }
        MultiGenerator.setListGenerators(gene);
    }

    public List<State> applyMultiobjectiveAlgorithmsBriefcase(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, SecurityException, InstantiationException, InvocationTargetException, Exception {
        List<State> noDominadas = new ArrayList<State>();
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        int executions = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        String fichero = "multiAlgorithmsBriefcase.txt";
        for (int i = 0; i < executions; i++) {
            //Configurar el algoritmo, dentro de BiCIAM
            Strategy.getStrategy().setStopexecute(new StopExecute());
            Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
            Strategy.getStrategy().setProblem(problem);
            Strategy.getStrategy().saveListBestStates = true;
            Strategy.getStrategy().calculateTime = true;
            Strategy.getStrategy().saveListStates = true;

            //Aqui va la configuracion espeficia del PA multiobjetivo
            //ECMO, ECMOR, ECMOD, BTMO, RSMO, RSMOU, NSGAII, MOGA, MOEADDE
            configurePAMO(iterations, true, true, false, true, false, false, false, false, false);
            createPlans();
            Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiobjectiveMultiGenerator); //cambia aqui el generator type por el multigenerator.
            noDominadas = Strategy.getStrategy().listRefPoblacFinal;
            Strategy.destroyExecute();
            MultiobjectiveMultiGenerator.destroyMultiobjectiveMultiGenerator();
        }
        return noDominadas;
    }

    public void configurePAMO(int countIterations, boolean ECMO, boolean ECMOR, boolean ECMOD, boolean BTMO, boolean RSMO, boolean RSMOU, boolean NSGAII, boolean MOGA, boolean MOEADDE)
            throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //ECMO, ECMOR, ECMOD, BTMO, RSMO, RSMOU, NSGAII, MOGA, MOEADDE
        Strategy.getStrategy().initializeGenerators();
        List<Generator> gene = new ArrayList<Generator>();
        NSGAII nsga = new NSGAII();
        MOGA moga = new MOGA();
        MOEADDE moeadde = new MOEADDE();
        if (ECMO) {
            MultiobjectiveStochasticHillClimbing ecmo = new MultiobjectiveStochasticHillClimbing();
            gene.add(ecmo);
        }
        if (ECMOR) {
            MultiobjectiveHillClimbingRestart ecmor = new MultiobjectiveHillClimbingRestart();
            ecmor.sizeNeighbors = 2;
            gene.add(ecmor);
        }
        if (ECMOD) {
            MultiobjectiveHillClimbingDistance ecmod = new MultiobjectiveHillClimbingDistance();
            gene.add(ecmod);
        }
        if (BTMO) {
            MultiobjectiveTabuSearch btmo = new MultiobjectiveTabuSearch();
            gene.add(btmo);
        }
        if (RSMO) {
            MultiCaseSimulatedAnnealing rsmo = new MultiCaseSimulatedAnnealing();
            rsmo.countIterationsT = 30;
            rsmo.alpha = 0.9;
            rsmo.tfinal = 0.0;
            rsmo.tinitial = 20.0;
            gene.add(rsmo);
        }
        if (RSMOU) {
            UMOSA rsmou = new UMOSA();
            rsmou.countIterationsT = 30;
            rsmou.alpha = 0.9;
            rsmou.tfinal = 0.0;
            rsmou.tinitial = 20.0;
            gene.add(rsmou);
        }
        if (NSGAII) {
            nsga.countRef = 20; //cantidad de individuos 20
            nsga.selectionType = SelectionType.TournamentSelection;
            nsga.crossoverType = CrossoverType.GenericCrossover;
            nsga.mutationType = MutationType.GenericMutation;
            nsga.PM = 0.5;
            nsga.PC = 0.9;
            gene.add(nsga);
        }
        if (MOGA) {
            moga.countRef = 50; //cantidad de individuos 100
            moga.selectionType = SelectionType.TournamentSelection;
            moga.crossoverType = CrossoverType.GenericCrossover;
            moga.mutationType = MutationType.GenericMutation;

            //moga.replaceType = ReplaceType.GenerationalReplace;
            moga.replaceType = ReplaceType.SteadyStateReplace;
            moga.PM = 0.9;
            moga.PC = 0.5;
            gene.add(moga);
        }
        if (MOEADDE) {
            moeadde.countRef = 100; //cantidad de individuos 100
            moeadde.crossoverType = CrossoverType.OnePointCrossover;
            moeadde.mutationType = MutationType.TowPointsMutation;
            moeadde.PS = 0.7;
            moeadde.PC = 0.6;
            moeadde.PM = 0.9;
            moeadde.T = (int) (0.1 * moeadde.countRef);
            moeadde.Nr = (int) (0.1 * moeadde.countRef);
            gene.add(moeadde);
        }
        MultiobjectiveMultiGenerator.setListGenerators(gene);

    }

    public List<State> GA(ProjectRoleState initialSolution, TeamFormationProblem problem) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {

        int ITERATIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));

        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                State bestState = null;
                Strategy.getStrategy().setStopexecute(new StopExecute());
                Strategy.getStrategy().setUpdateparameter(new UpdateParameter());
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;

                //Configuracion del algoritmo genetico
                //cantidad de individuos
                GeneticAlgorithm.countRef = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("CountRef"));
                //Probabilidad de cruzamiento o combinacion
                GeneticAlgorithm.PC = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PC"));
                //Probabilidad de mutacion
                GeneticAlgorithm.PM = Float.parseFloat(ResourceBundle.getBundle("/algorithmConf").getString("PM"));

                GeneticAlgorithm.selectionType = SelectionType.TournamentSelection;
                GeneticAlgorithm.truncation = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("GeneticAlgorithmTruncation"));
                GeneticAlgorithm.crossoverType = CrossoverType.GenericCrossover;
                GeneticAlgorithm.mutationType = MutationType.GenericMutation;
                GeneticAlgorithm.replaceType = ReplaceType.SteadyStateReplace;

                Strategy.getStrategy().executeStrategy(ITERATIONS, 1, GeneratorType.RandomSearch);

                bestState = Strategy.getStrategy().getBestState();

                if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                    sol.add(bestState);
                } else {
                    bestState = prepareSolution();
                    if (bestState != null) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }
        }
        return sol;
    }

    public void createPlans() throws Exception {
        /*List<Plan> plans = new ArrayList<Plan>();

        ArrayList<Float> weights = new ArrayList<>();
        ArrayList<Float> taxs = new ArrayList<>();
        ArrayList<Float> weights2 = new ArrayList<>();

        weights.add(50f);

        weights.add(50f);

        weights.add(50f);

        weights2.add(50f);
        weights2.add(0.0f);
        weights2.add(0.0f);
        weights2.add(0.0f);
        weights2.add(0.0f);
        weights2.add(10f);
        weights2.add(0.0f);
        weights2.add(30f);
        weights2.add(0.0f);

        for (int i = 0; i < 9; i++) {

            taxs.add(0.1f);
        }

        Plan plan1 = new Plan(1, 1000, taxs, 10, weights, SelectionTypePortfolio.Roulette);
        //Plan plan2 = new Plan(5001, 10000, taxs, 10, 0.2f, weights2);
        plans.add(plan1);
        //plans.add(plan2);

        if (MultiobjectiveMultiGenerator.validateIterations(plans, 1000)) {
            MultiobjectiveMultiGenerator.setPlans(plans);
        } else {
            throw new Exception("No coinciden las iteraciones");
        }*/
    }

    /**
     * Mejorar solucion aplicando el metodo MultiobjectiveStochasticHillClimbing
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyMultiobjectiveStochasticHC(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException {

        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {

                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiobjectiveStochasticHillClimbing);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();

            }
        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el metodo MultiobjectiveHCRestart
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyMultiobjectiveHCRestart(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException {

        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));

        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();

            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;
                MultiobjectiveHillClimbingRestart.sizeNeighbors = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("MultiobjectiveHCRestartSizeNeighbors"));

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiobjectiveHillClimbingRestart);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();

            }
        }
        return sol;
    }

    /**
     * Mejorar solucion aplicando el metodo MultiobjectiveHCDistance
     *
     * @param initialSolution
     * @param problem
     * @return
     * @throws java.io.IOException
     */
    public List<State> applyMultiobjectiveHCDistance(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();
            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;
                MultiobjectiveHillClimbingDistance.sizeNeighbors = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("MultiobjectiveHCDistanceSizeNeighbors"));

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiobjectiveHillClimbingDistance);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();

            }
        }
        return sol;
    }

    public List<State> applyMultiobjectiveTabuSearch(ProjectRoleState initialSolution, TeamFormationProblem problem) throws IOException {
        int EXECUTIONS = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("executions"));
        int iterations = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("iterations"));
        List<State> sol = null;
        if (initialSolution != null) {
            sol = new ArrayList<>();
            for (int i = 0; i < EXECUTIONS; i++) {
                configStrategy(problem);
                Strategy.getStrategy().saveListBestStates = true;
                Strategy.getStrategy().saveListStates = true;
                Strategy.getStrategy().saveFreneParetoMonoObjetivo = true;
                TabuSolutions.maxelements = Integer.parseInt(ResourceBundle.getBundle("/algorithmConf").getString("MultiobjectiveTabuSolutionsMaxelements"));
                String format = ResourceBundle.getBundle("/algorithmConf").getString("decimalFormat");
                DecimalFormat df = new DecimalFormat(format);

                try {
                    Strategy.getStrategy().executeStrategy(iterations, 1, GeneratorType.MultiobjectiveTabuSearch);
                } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(TeamFormationStepThreeImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                List<State> noDominadas = Strategy.getStrategy().listRefPoblacFinal;
                State bestState = Strategy.getStrategy().getBestState();

                //Mostrando las soluciones no dominadas y guardando soluciones en el fichero de texto
                for (State noDominada : noDominadas) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(noDominada)) {
                        sol.add(noDominada);
                    }
                }

                if (sol.isEmpty()) {
                    if (Strategy.getStrategy().getProblem().getCodification().validState(bestState)) {
                        sol.add(bestState);
                    }
                }
                Strategy.destroyExecute();
            }
        }
        return sol;
    }
}

