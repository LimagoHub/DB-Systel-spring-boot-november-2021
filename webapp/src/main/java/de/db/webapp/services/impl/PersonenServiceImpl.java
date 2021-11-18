package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.models.Person;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class PersonenServiceImpl implements PersonenService {


    private final PersonenRepository repository;
    private final PersonMapper mapper;
    private final Set<String> antipathen;





    /*
            wenn person null -> PSE
            wenn vorname null -> PSE
            wenn vorname weniger als 2 Zeichen -> PSE
            wenn nachname null -> PSE
            wenn nachname weniger als 2 Zeichen -> PSE

            wenn vorname Attila -> PSE

            wenn Unexpected Exception in underlying service -> PSE

            passe to Repo

            true bei Update
            false bei insert
         */
    @Override

    public boolean speichereOderAendere(Person person) throws PersonenServiceException {
        try {
            return speichernImpl(person);
        } catch (RuntimeException e) {
            // Loggen !!!!!!
            throw new PersonenServiceException("Fehler beim Speichern", e);
        }
    }

    private boolean speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);
        boolean result = repository.existsById(person.getId());
        repository.save(mapper.convert(person));
        return result;
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(antipathen.contains(person.getVorname())) throw new PersonenServiceException("Antipath");
    }

    private void validatePerson(Person person) throws PersonenServiceException {
        if(person == null) throw new PersonenServiceException("Person darf nicht null sein");
        if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
        if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");
    }

    @Override
    public boolean loesche(Person person) throws PersonenServiceException {
        return loesche(person.getId());
    }

    @Override
    public boolean loesche(String id) throws PersonenServiceException {
        try {
            if(repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    @Override
    public Optional<Person> findeNachId(String id) throws PersonenServiceException {
        try {
            return repository.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repository.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }
}
