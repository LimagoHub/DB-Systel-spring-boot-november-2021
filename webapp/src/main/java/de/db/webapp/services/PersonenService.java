package de.db.webapp.services;

import de.db.webapp.services.models.Person;

import java.util.Optional;

public interface PersonenService {

    boolean speichereOderAendere(Person person) throws PersonenServiceException;
    boolean loesche(Person person) throws PersonenServiceException;
    boolean loesche(String id) throws PersonenServiceException;
    Optional<Person> findeNachId(String id)  throws PersonenServiceException;
    Iterable<Person> findeAlle() throws PersonenServiceException;
}
