package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.PersonEntity;
import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import com.tesis.teamsoft.persistence.entity.RoleEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FixedWorker {

    ProjectEntity project;
    RoleEntity role;
    PersonEntity boss;
}
