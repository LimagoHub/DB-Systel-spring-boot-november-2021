package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mappers.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.glassfish.jersey.uri.UriComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(DemoControllerPath.BASE_PATH)
//@RequestScope
//@SessionScope (Bitte nicht verwenden, wenn Skalierbarkeit und Hochverfügbarkeit gewünscht)
@AllArgsConstructor
public class PersonenController {

    private final PersonenService service;
    private final PersonDTOMapper mapper;

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Bad Request" )
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path= "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> findPersonByID(@PathVariable String id) throws Exception{

        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));

    }
    @ApiResponse(responseCode = "200", description = "Liste wurde erstellr")
    @ApiResponse(responseCode = "400", description = "Bad Request" )
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path= "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDTO>> findAll(
            @RequestParam (required = false) String vorname,
            @RequestParam (required = false) String nachname
    )throws Exception{



        return ResponseEntity.ok(mapper.convert(service.findeAlle()));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable  String id)throws Exception{
        if(service.loesche(id)) {

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody PersonDTO dto,  UriComponentsBuilder builder) throws Exception{
        if(! service.speichereOderAendere(mapper.convert(dto))) {
            UriComponents component = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
            return ResponseEntity.created(component.toUri()).build();
        }

        return ResponseEntity.ok().build();
    }

//    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> saveOrUpdateNotIdempotent(@RequestBody PersonDTO dto, UriComponentsBuilder builder){
//        dto.setId(UUID.randomUUID().toString());
//        // Servicecall
//        UriComponents component = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
//        return ResponseEntity.created(component.toUri()).build();
//    }

    @PostMapping(value = "/to-upper", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> toUpper(@RequestBody  PersonDTO source) {
        source.setVorname(source.getVorname().toUpperCase());
        source.setNachname(source.getNachname().toUpperCase());
        return ResponseEntity.ok(source);
    }


}

