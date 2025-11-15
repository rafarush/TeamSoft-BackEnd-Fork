package com.tesis.teamsoft.metaheuristics.util;

import com.tesis.teamsoft.persistence.entity.AgeGroupEntity;
import com.tesis.teamsoft.persistence.entity.PersonEntity;
import com.tesis.teamsoft.persistence.entity.ReligionEntity;

import java.util.ArrayList;
import java.util.List;

public class FactorEvaluation {

    static double calculateCoef(int teamWorkerListSize, List<Integer> amountTeamList, int amountInSearchArea) {
        double minValueOrganizationList = 1, minValueTeamList = 1, sum = 0;
        double maxValue;
        double normalizeCoef;

        maxValue = Math.min(teamWorkerListSize, amountInSearchArea);

        if(maxValue > 1){
            for (Integer interger : amountTeamList) {
                sum += (interger - minValueTeamList) / (maxValue - minValueOrganizationList);
            }

            normalizeCoef = amountTeamList.isEmpty() ? 0 : sum / amountTeamList.size();

            return normalizeCoef;
        }

        return 0.0;
    }
    //==========================================================================================================================


    //Clase encargada del calculo de Evaluation de AgeGroup
    //==========================================================================================================================
    public static class AgeGroupEvaluation {

        public static double ageGroupEvaluation(List<Object> projects, List<PersonEntity> organizationWorkerList) {
            List<PersonEntity> teamWorkerList = new ArrayList<>();
            List<Integer> amountTeamList = new ArrayList<>();

            int amountAgeGroupSearchArea = ageGroupAmount(organizationWorkerList);

            for (Object project : projects) {
                teamWorkerList = ObjetiveFunctionUtil.ProjectWorkers((ProjectRole) project);
                amountTeamList.add(ageGroupAmount(teamWorkerList));
            }

            return calculateCoef(teamWorkerList.size(), amountTeamList, amountAgeGroupSearchArea);
        }

        public static double ageGroupBalanceEvaluation(List<Object> projects, List<PersonEntity> organizationWorkerList) {
            List<PersonEntity> teamWorkerList;
            List<Integer> amountTeamList = new ArrayList<>();

            double balanceCoef = 0, sum = 0, sumCoef = 0;
            int amountAgeGroupSearchArea = ageGroupAmount(organizationWorkerList);

            for (Object project : projects) {
                teamWorkerList = ObjetiveFunctionUtil.ProjectWorkers((ProjectRole) project);
                int amount = ageGroupAmount(teamWorkerList);
                amountTeamList.add(amount);
                sum += amount;
            }

            if (!projects.isEmpty()) {
                double average = sum / projects.size();

                for(Integer integer: amountTeamList){
                    sumCoef += Math.abs(average - integer);
                }

                balanceCoef = sumCoef / (projects.size() * amountAgeGroupSearchArea);
            }

            return balanceCoef;
        }

        private static int ageGroupAmount(List<PersonEntity> personList) {
            List<AgeGroupEntity> ageGroupList = new ArrayList<>();

            for (PersonEntity p : personList) {
                if (ageGroupList.stream().noneMatch((ag) -> (ag == p.getAgeGroup()))) {
                    ageGroupList.add(p.getAgeGroup());
                }
            }

            return ageGroupList.size();
        }
    }
    //==========================================================================================================================


    //Clase encargada del calculo de Evaluation de AgeGroup
    //==========================================================================================================================
    public static class ReligionEvaluation {

        public static double religionEvaluation(List<Object> projects, List<PersonEntity> organizationWorkerList) {
            List<PersonEntity> teamWorkerList = new ArrayList<>();
            List<Integer> amountTeamList = new ArrayList<>();

            int amountReligionSearchArea = religionAmount(organizationWorkerList);

            for (Object project : projects) {
                teamWorkerList = ObjetiveFunctionUtil.ProjectWorkers((ProjectRole) project);
                amountTeamList.add(religionAmount(teamWorkerList));
            }

            return calculateCoef(teamWorkerList.size(), amountTeamList, amountReligionSearchArea);
        }

        public static double religionBalanceEvaluation(List<Object> projects, List<PersonEntity> organizationWorkerList) {
            List<PersonEntity> teamWorkerList;
            List<Integer> amountTeamList = new ArrayList<>();
            double balanceCoef = 0, sum = 0, sumCoef = 0;

            int amountReligionSearchArea = religionAmount(organizationWorkerList);

            for (Object project : projects) {
                teamWorkerList = ObjetiveFunctionUtil.ProjectWorkers((ProjectRole) project);
                int amount = religionAmount(teamWorkerList);
                amountTeamList.add(amount);
                sum += amount;
            }

            if (!projects.isEmpty()) {
                double average = sum / projects.size();

                for (Integer integer : amountTeamList) {
                    sumCoef += Math.abs(average - integer);
                }

                balanceCoef = sumCoef / (projects.size() * amountReligionSearchArea);
            }

            return balanceCoef;
        }

        private static int religionAmount(List<PersonEntity> personList) {
            List<ReligionEntity> religionList = new ArrayList<>();

            for (PersonEntity p : personList) {
                if (religionList.stream().noneMatch((rel) -> (rel.equals(p.getReligion())))) {
                    religionList.add(p.getReligion());
                }
            }

            return religionList.size();
        }
    }
}
