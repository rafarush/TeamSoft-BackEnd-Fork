package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.RoleEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SelectedRoleIncompatibility {

    private RoleEntity roleAFk;
    private RoleEntity roleBFk;
}
