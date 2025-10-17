package com.tesis.teamsoft.presentation.controller;


import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import com.tesis.teamsoft.service.implementation.ReligionServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Religions")
@RequestMapping("/religion")
public class ReligionController {

    @Autowired
    ReligionServiceImpl religionService;

    @RequestMapping(value = "/create_religion", method = RequestMethod.POST)
    public ResponseEntity<?> createReligion(@RequestBody ReligionDTO.ReligionCreateDTO religionDTO) {
        return new ResponseEntity<>(religionService.saveReligion(religionDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/udpate_religion/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateReligion(@RequestBody ReligionDTO.ReligionCreateDTO religionDTO, @PathVariable Long id) {
        return new ResponseEntity<>(religionService.updateReligion(religionDTO, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete_religion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReligion(@PathVariable Long id) {
        return new ResponseEntity<>(religionService.deleteReligion(id), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/findAll_religion", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAllReligions() {
        return new ResponseEntity<>(religionService.findAllByOrderByIdAsc(), HttpStatus.OK);
    }


    @RequestMapping(value = "findByID_religion/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(religionService.findReligionById(id), HttpStatus.OK);
    }

}
