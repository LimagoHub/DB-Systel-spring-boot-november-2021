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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"classpath:/create.sql", "classpath:/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService serviceMock;

    @Test
    void test1() throws Exception{
        // Arrange
        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        Mockito.when(serviceMock.findeNachId(Mockito.anyString())).thenReturn(optionalPerson);
        // Act
        PersonDTO personDTO = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        // Assertion
        assertEquals("John",personDTO.getVorname());
    }
    @Test
    void test2() throws Exception{
        // Arrange
        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        Mockito.when(serviceMock.findeNachId(Mockito.anyString())).thenReturn(optionalPerson);
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
        Mockito.when(serviceMock.findeNachId(Mockito.anyString())).thenReturn(optionalPerson);
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

        Mockito.when(serviceMock.findeNachId(Mockito.anyString())).thenReturn(Optional.empty());
        // Act
        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);



        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
    }
}