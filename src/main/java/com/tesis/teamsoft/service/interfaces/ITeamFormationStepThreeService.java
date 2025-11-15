package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.pojos.TeamFormationParameters;
import com.tesis.teamsoft.presentation.dto.TreeNode;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamFormationStepThreeService {

    TreeNode getTeam(TeamFormationParameters parameters) throws Exception;
}
