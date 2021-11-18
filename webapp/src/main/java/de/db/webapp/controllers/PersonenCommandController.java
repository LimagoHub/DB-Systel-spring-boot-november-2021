package de.db.webapp.controllers;

import de.db.webapp.application.PersonenHandler;
import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mappers.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(DemoControllerPath.BASE_PATH)
//@RequestScope
//@SessionScope (Bitte nicht verwenden, wenn Skalierbarkeit und Hochverfügbarkeit gewünscht)
@AllArgsConstructor
public class PersonenCommandController {

    private final PersonenHandler handler;




    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable  String id)throws Exception{
           handler.handleDelete(id);

           return ResponseEntity.ok().build();

    }

    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody PersonDTO dto,  UriComponentsBuilder builder) throws Exception{
        handler.handleSave(dto);

        return ResponseEntity.ok().build();
    }



}

