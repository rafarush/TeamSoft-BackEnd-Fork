package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.pojos.TeamFormationParameters;
import com.tesis.teamsoft.presentation.dto.TreeNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeamFormationStepThreeService {

    TreeNode getTeam(TeamFormationParameters parameters, List<Long> projectsIDs, List<Long> groupIDs) throws Exception;
}
