package com.tesis.teamsoft.presentation.controller;

import com.tesis.teamsoft.service.implementation.TeamFormationStepThreeImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.tesis.teamsoft.presentation.dto.TeamFormationDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "TeamFormation")
@RequestMapping("/teamFormation")
public class TeamFormationController {

    @Autowired
    private TeamFormationStepThreeImpl teamFormationStepThree;

    @PostMapping()
    @PreAuthorize("hasRole('EXPERIMENTADOR') OR hasRole('DIRECTIVO_TECNICO')")
    public ResponseEntity<?> findAgeGroupById(@RequestBody TeamFormationDTO dto) {

        try {
            return new ResponseEntity<>(teamFormationStepThree.getTeam(dto.getTeamFormationParameters()
                    , dto.getProjectsIDs(), dto.getGroupIDs()), HttpStatus.FOUND);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
