package com.tesis.teamsoft.presentation.dto;

import com.tesis.teamsoft.pojos.TeamFormationParameters;
import lombok.Data;

import java.util.List;

@Data
public class TeamFormationDTO {
    TeamFormationParameters teamFormationParameters;
    List<Long> projectsIDs;
    List<Long> groupIDs;
}
