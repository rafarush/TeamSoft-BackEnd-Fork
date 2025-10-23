package com.tesis.teamsoft.presentation.controller;

import com.tesis.teamsoft.presentation.dto.CompetenceDTO;
import com.tesis.teamsoft.service.implementation.CompetenceServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Competence")
@RequestMapping("/competence")
public class CompetenceController {

    @Autowired
    private CompetenceServiceImpl competenceService;

    @RequestMapping(value = "/create_competence", method = RequestMethod.POST)
    public ResponseEntity<?> createCompetence(@Valid @RequestBody CompetenceDTO.CompetenceCreateDTO competenceDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try{
            return new ResponseEntity<>(competenceService.saveCompetence(competenceDTO), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update_competence/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCompetence(@Valid @RequestBody CompetenceDTO.CompetenceCreateDTO competenceDTO,
                                              BindingResult bindingResult,
                                              @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(competenceService.updateCompetence(competenceDTO, id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete_competence/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCompetence(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(competenceService.deleteCompetence(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/findAll_competence", method = RequestMethod.GET)
    public ResponseEntity<?> findAllCompetence() {
        try {
            return new ResponseEntity<>(competenceService.findAllByOrderByIdAsc(), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findByID_competence/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findCompetenceById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(competenceService.findCompetenceById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
