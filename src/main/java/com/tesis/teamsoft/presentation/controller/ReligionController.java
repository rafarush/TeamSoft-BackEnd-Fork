package com.tesis.teamsoft.presentation.controller;


import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import com.tesis.teamsoft.service.implementation.ReligionServiceImpl;
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
@Tag(name = "Religions")
@RequestMapping("/religion")
public class ReligionController {

    @Autowired
    private ReligionServiceImpl religionService;

    @RequestMapping(value = "/create_religion", method = RequestMethod.POST)
    public ResponseEntity<?> createReligion(@Valid @RequestBody ReligionDTO.ReligionCreateDTO religionDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError ->
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try{
            return new ResponseEntity<>(religionService.saveReligion(religionDTO), HttpStatus.CREATED);
        }catch(Exception e){
            Map<String, String> errors = new HashMap<>();
            errors.put("error", e.getMessage());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/udpate_religion/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateReligion(@Valid @RequestBody ReligionDTO.ReligionCreateDTO religionDTO, BindingResult bindingResult, @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError ->
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(religionService.updateReligion(religionDTO, id), HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        catch(RuntimeException e){
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete_religion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReligion(@PathVariable Long id) {
       try {
           return new ResponseEntity<>(religionService.deleteReligion(id), HttpStatus.NO_CONTENT);

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

    @RequestMapping(value = "/findAll_religion", method = RequestMethod.GET)
    public ResponseEntity<?> findAllReligions() {
        try {
            return new ResponseEntity<>(religionService.findAllByOrderByIdAsc(), HttpStatus.OK);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "findByID_religion/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(religionService.findReligionById(id), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

    }

}
