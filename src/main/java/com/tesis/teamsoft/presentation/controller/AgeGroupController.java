package com.tesis.teamsoft.presentation.controller;

import com.tesis.teamsoft.persistence.entity.AgeGroupEntity;
import com.tesis.teamsoft.presentation.dto.AgeGroupDTO;
import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import com.tesis.teamsoft.service.implementation.AgeGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "AgeGroups")
@RequestMapping("/ageGroup")
public class AgeGroupController {

    @Autowired
    private AgeGroupService ageGroupService;

    @RequestMapping(value = "/create_ageGroup", method = RequestMethod.POST)
    public ResponseEntity<?> createAgeGroup(@Valid @RequestBody AgeGroupDTO.AgeGroupCreateDTO ageGroupDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(ageGroupService.saveAgeGroup(ageGroupDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update_ageGroup/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAgeGroup(@Valid @RequestBody AgeGroupDTO.AgeGroupCreateDTO ageGroupDTO
            , BindingResult bindingResult
            , @PathVariable long id) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(ageGroupService.updateAgeGroup(ageGroupDTO, id), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete_ageGroup/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReligion(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(ageGroupService.deleteAgeGroup(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/findAll_ageGroup", method = RequestMethod.GET)
    public ResponseEntity<?> findAllReligions() {
        try {
            return new ResponseEntity<>(ageGroupService.findAllByOrderByIdAsc(), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error> ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "findByID_ageGroup/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(ageGroupService.findAgeGroupById(id), HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error: ", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}