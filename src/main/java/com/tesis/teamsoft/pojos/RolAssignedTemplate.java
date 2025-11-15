package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.RoleEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolAssignedTemplate {

    private RoleEntity role;
    private List<PersonAssignedTemplate> peopleAssigned;
}
