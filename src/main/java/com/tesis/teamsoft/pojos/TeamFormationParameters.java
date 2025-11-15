package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.*;
import lombok.*;
import problem.extension.TypeSolutionMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamFormationParameters implements Serializable {

    /////////////////////////////////////////////////////////////////
    //////////////////////PASO 1/////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    private ArrayList<PersonGroupEntity> groupList = new ArrayList<>(); //area de busqueda
    private boolean confRole = false;
    public int maximunRoles = 0; //cantidad maxima de roles que puede asumir una persona
    private boolean confAllGroup = false;
    private boolean onlyOneProject = false; //cada persona se asigna a un solo proyecto
    private int confFormMode = 0;
    private ArrayList<PersonPerProjectAmount> ppg = new ArrayList();

    public boolean isMinimunRoles = false; //cantidad minima de roles a asumir una persona
    public int minimumRole = 1; //cantidad minima de roles a asumir una persona
    public boolean bossNeedToBeAssignedToAnotherRoles = false;
    //////////////////////////////////////////////////////////////////////////
    ///APLICAR ESTAS FUNCIONES OBJETIVO///////////////
    private boolean maxCompetences = false;
    private boolean maxInterests = false;
    private boolean maxProjectInterests = false;
    private boolean maxMbtiTypes = false;
    private boolean maxBelbinRoles = false;
    private boolean minIncomp = false;
    private boolean minCostDistance = false;
    private boolean takeWorkLoad = false;
    private boolean maxMultiroleTeamMembers = false;
    private boolean maxSex = false;
    private boolean minSex = false;
    private boolean heterogeneousTeams = false;
    private boolean homogeneousTeams = false;
    private boolean maxReligion = false;
    private boolean minReligion = false;
    private boolean maxAgeHeterogeneity = false;
    private boolean minAgeHomogeneity = false;

     
    private boolean balanceCompetences = false;
    private boolean balanceInterests = false;
    private boolean balanceProjectInterests = false;
    private boolean balanceMbtiTypes = false;
    private boolean balanceBelbinRoles = false;
    private boolean balanceSynergy = false;
    private boolean balanceCostDistance = false;
    private boolean balancePersonWorkload = false;
    private boolean balanceMultiroleTeamMembers = false;
    private boolean balanceMaximizeSexFactor = false;
    private boolean balanceMinimizeSexFactor = false;
    private boolean balanceHeterogeneousTeams = false;
    private boolean balanceHomogeneousTeams = false;
    private boolean balanceMaximizeReligion = false;
    private boolean balanceMinimizeReligion = false;
    private boolean balanceMaximizeAgeHeterogeneity = false;
    private boolean balanceMinimizeAgeHomogeneity = false;

    //para la funcion maxCompetences: considerar los objetivos...
    private boolean maxCompetencesByProject = false; //maximizar las competencias por proyecto
    private float maxCompetencesByProjectWeight = 0; // peso asignado a las competencias por cada proyecto
    //    private boolean maxRoleExperience = false; //si se tiene en cuenta la experiencia en el rol
//    private float maxRoleExperienceWeight = 0; //peso dado a la experiencia en el desempeño del rol
    //para la experiencia en el desempe;o del rol
    private boolean noTimesInRol = false; //si se tiene en cuenta el numero de proyectos en que ha desempeñado el rol
    private boolean evaluationInRol = false; //si se tiene en cuenta la evaluacion en el desempeño del rol

    /////PESO PARA LAS FUNCIONES OBJETIVO///////////////
    private float maxCompetencesWeight = 0;
    private float maxInterestsWeight = 0;
    private float maxProjectInterestsWeight = 0;
    private float maxMbtiTypesWeight = 0;
    private float maxBelbinWeight = 0;
    private float minIncompWeight = 0;
    private float minCostDistanceWeight = 0;
    private float workLoadWeight = 0;
    private float maxMultiroleTeamMembersWeight = 0;
    private float maxSexWeight = 0;
    private float minSexWeight = 0;
    private float heterogeneousTeamsWeight = 0;
    private float homogeneousTeamsWeight = 0;
    private float maxAgeHeterogeneityWeight = 0;
    private float minAgeHomogeneityWeight = 0;
    private float maxReligionWeight = 0;
    private float minReligionWeight = 0;

    private float balanceCompetenceWeight = 0;
    private float balanceWorkLoadWeight = 0;
    private float balanceSynergyWeight = 0;
    private float balanceCostDistanceWeight = 0;
    private float balanceInterestWeight = 0;
    private float balanceProjectInterestWeight = 0;
    private float balanceMbtiTypesWeight = 0;
    private float balanceBelbinWeight = 0;
    private float balanceMultiroleTeamMembersWeight = 0;
    private float balanceMaximizeSexFactorWeight = 0;
    private float balanceMinimizeSexFactorWeight = 0;
    private float balanceHeterogeneousTeamsNacionalityFactorWeight = 0;
    private float balanceHomogeneousTeamsNacionalityFactorWeight = 0;
    private float balanceMaximizeReligionWeight = 0;
    private float balanceMinimizeReligionWeight = 0;
    private float balanceMaximizeAgeHeterogeneityWeight = 0;
    private float balanceMinimizeAgeHomogeneityWeight = 0;

    private ArrayList<PersonEntity> searchArea = new ArrayList<>();
    private ArrayList<FixedWorker> fixedWorkers = new ArrayList<>();
    /////////////////////////////////////////////////////////////////
    //////////////////////PASO 2 en adelante/////////////////////////
    ///////////////////////////////////////////////////////////////
    private List<ProjectRoleCompetenceTemplate> projects = new ArrayList<>(); //lista de proyectos seleccionados en paso 1 del wizard

    private LevelsEntity maxLevel = new LevelsEntity(); //maximo nivel de competencia declarado por la entidad
    private LevelsEntity minLevel = new LevelsEntity(); //minimo nivel de competencia declarado por la entidad

    private ConflictIndexEntity maxConflictIndex = new ConflictIndexEntity(); //maximo indice de conflicto
    private CostDistanceEntity maxCostDistance = new CostDistanceEntity(); // maximo costo de distancia

    //    private RoleEval maxEvaluation = new RoleEval(); //minima evaluacion definida para un rol
//    private RoleEval minEvaluation = new RoleEval(); //maxima evaluacion definida para un rol
    /////VARIABLES PARA LA PESTAÑA CARGA DE TRABAJO DEL ROL///////////////
    private boolean canBeProjectBoss = false; //Permitir o no que ya este asignado como jefe de proyecto
    private boolean takeCustomPersonWorkLoad = false; //definir la carga maxima que puede tener el trabajador
    private RoleLoadEntity maxRoleLoad = new RoleLoadEntity(); //Carga maxima que puede tener el trabajador

    ////////VARIABLES PARA LA PESTAÑA CARACTERÍSTICAS PSICOLÓGICAS//////////////////////////
    private boolean preferBelbin = false; // si prefiere los roles de belbin
    private boolean preferMyersBrigs = false; // si prefiere el subtipo E??J de Myers-Brigs
    private int cantCerebro = 0; //cantidad de min de personas con rol cerebro en el equipo

    ////////VARIABLES PARA LA PESTAÑA INTERÉS POR EL EQUIPO//////////////////////////
    private boolean bossTeamInterest = false;

    // para la solucion considerar 
    private final TypeSolutionMethod solutionMethodOptionBoss = TypeSolutionMethod.FactoresPonderados; // Factores pnderados siempre para jefe de proyecto

    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////PASO 3//////////FORMACION DE EQUIPOS////////////////
    /////////////////////////////////////////////////////////////////////////////
    // para la solucion considerar 
    private TypeSolutionMethod solutionMethodOptionTeam = TypeSolutionMethod.FactoresPonderados; // Metoodo de solucion a usar "PONDERAR";PRIORIZAR;IGUALAR. Ponderar por defecto
    private int solutionAlgorithm; //Opcion de algoritmo de solucion a emplear (case 1: StochasticMutiObjetiveHillClimbing si factores ponderados y HillClimbing si Priorizar)

    //Sinergia
    private boolean anyIncompatibility = false; //no permitir ninguna incompatibilidad
    private boolean anySelectedIncompatibility = false; //no permitir ninguna incompatibilidad seleccionada
    private List<SelectedWorkerIncompatibility> sWI = new ArrayList<>(); //Incompatibilidades entre los Trabajadores
    private List<SelectedRoleIncompatibility> sRI = new ArrayList<>(); //Incompatibilidades entre los Roles

    ////////////////////////////TAB CARACTERISTICAS PSYCOLOGICAS////////////////////////////
    private boolean allBelbinRoles = false; ///exigir la precencia de todos los roles de Belbin
    private boolean demandNBrains = false;//al menis nBrains personas con el rol cerebro
    private int countBrains = 0;//cant nBrains personas con el rol cerebro
    private boolean balanceBelbinCategories = false; //balancear categorias de roles de belbin
    private boolean allBelbinCategories = false; //precensia de todas las categorias de Belbin
    private String actionMentalOper = "&gt;"; // más menos o igual numero de roles de accion que mentales (>) por defecto....("&gt;" equals to ">" )
    private String mentalSocialOper = "&gt;"; // más menos o igual numero de roles de mentales que sociales (>) por defecto....("&gt;" equals to ">" )
}
