package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"classpath:/create.sql", "classpath:/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService serviceMock;

    private final String validUUID = "b2e24e74-8686-43ea-baff-d9396b4202e0";
    private final PersonDTO validPersonDTO = PersonDTO.builder().id(validUUID).vorname("John").nachname("Doe").build();

    @Test
    void test1() throws Exception{
        // Arrange
        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        PersonDTO personDTO = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        // Assertion
        assertEquals("John",personDTO.getVorname());
    }
    @Test
    void test2() throws Exception{
        // Arrange
        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        String string = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);

//        // Assertion
//        assertEquals("John",personDTO.getVorname());
        System.out.println(string);
    }
    @Test
    void test3() throws Exception{
        // Arrange
        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        PersonDTO personDTO = entity.getBody();

        // Assertion
        assertEquals("John",personDTO.getVorname());
        assertEquals(HttpStatus.OK,entity.getStatusCode());
    }
    @Test
    void test4() throws Exception{
        // Arrange

        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.empty());
        // Act
        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);



        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
    }

    @Test
    void test5() throws Exception{
        // Arrange

        Iterable<Person> personen = List.of(Person.builder().id("1").vorname("John").nachname("Doe").build(),Person.builder().id("2").vorname("John").nachname("Rambo").build());
        when(serviceMock.findeAlle()).thenReturn(personen);
        // Act
        ResponseEntity<Iterable<PersonDTO>> entity =
                restTemplate.exchange(
                        "/v1/personen",
                        HttpMethod.GET,
                        null
                        , new ParameterizedTypeReference<Iterable<PersonDTO>>() {
                        });



        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(2,StreamSupport.stream(entity.getBody().spliterator(), false).count());
        ;
    }

    @Test
    void test6() throws Exception{
        // Arrange

        HttpEntity<PersonDTO> requestEntity =new HttpEntity<>(validPersonDTO);
        when(serviceMock.speichereOderAendere(any())).thenReturn(true);
        // Act
        ResponseEntity<Void> entity =
                restTemplate.exchange(
                        "/v1/personen",
                        HttpMethod.PUT,
                        requestEntity
                        , Void.class);



        assertEquals(HttpStatus.OK,entity.getStatusCode());
        verify(serviceMock, times(1)).speichereOderAendere(Person.builder().id(validUUID).vorname("John").nachname("Doe").build());

    }
}