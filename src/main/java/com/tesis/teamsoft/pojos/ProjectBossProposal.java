package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Para almacenar la propuesta de jefe de proyecto
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectBossProposal {

    private ProjectEntity project;
    private List<BossProposal> bossProposalList;
}
