package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonMapper;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository repoMock;
    @Mock
    private PersonMapper mapperMock;
    @Mock
    private Set<Person> antipathenMock;

    private final Person validPerson = Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build();


    @Test
    void speichern_parameterNull_throwsPersonenServiceException() {
        // Arrange

        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(null));
        // Assertion
        assertEquals("Person darf nicht null sein", ex.getMessage());
    }
    @Test
    void speichern_VornameNull_throwsPersonenServiceException() {
        // Arrange
        Person p = Person.builder().id("012345678901234567890123456789012345").vorname(null).nachname("Doe").build();
        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(p));
        // Assertion
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_VornameToShort_throwsPersonenServiceException() {
        // Arrange
        Person p = Person.builder().id("012345678901234567890123456789012345").vorname("J").nachname("Doe").build();
        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(p));
        // Assertion
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_NachnameNull_throwsPersonenServiceException() {
        // Arrange
        Person p = Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname(null).build();
        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(p));
        // Assertion
        assertEquals("Nachname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_NachnameToShort_throwsPersonenServiceException() {
        // Arrange
        Person p = Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("D").build();
        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(p));
        // Assertion
        assertEquals("Nachname zu kurz", ex.getMessage());
    }

    @Test
    void speichern_Antipath_throwsPersonenServiceException() {
        // Arrange
        Person p = Person.builder().id("012345678901234567890123456789012345").vorname("Attila").nachname("Doe").build();
        when(antipathenMock.contains(anyString())).thenReturn(true);

        // Act & Assert
        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichereOderAendere(p));
        // Assertion
        assertEquals("Antipath", ex.getMessage());
    }
}