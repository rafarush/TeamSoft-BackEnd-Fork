package com.tesis.teamsoft.presentation.controller.external;


import com.tesis.teamsoft.external.combination_mod.services.implementations.CombinationService;
import com.tesis.teamsoft.presentation.dto.TeamProposalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@Tag(name = "Combination Module API", description = "Endpoints to consume the Combination Module API")
@RequestMapping("/combination_mod")
public class CombinationModController {
    private final CombinationService combinationService;

    @Autowired
    public CombinationModController(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    @PostMapping("/combine")
    @Operation(
            summary = "Combine team members",
            description = "Combine teams using the combination module"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "502", description = "External API Error"),
            @ApiResponse(responseCode = "504", description = "External API Timeout")
    })
    public ResponseEntity<?> combineTeams(@RequestBody TeamProposalDTO inputDTO) {
        try {
            TeamProposalDTO response = combinationService.fetchData(inputDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.warn("Request failed for message: {}", inputDTO, e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(Map.of("Error", "External API Error"));
        }
    }


}
