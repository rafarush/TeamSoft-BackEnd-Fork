package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.PersonEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SelectedWorkerIncompatibility {

    private PersonEntity workerAFk;
    private PersonEntity workerBFk;
}
