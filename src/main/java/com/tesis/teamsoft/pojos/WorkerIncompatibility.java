package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.PersonEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkerIncompatibility {
    
    private PersonEntity workerConflict;
    private String Conflict;
}
