package de.db.webapp.application;


import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mappers.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class)
@AllArgsConstructor
public class PersonenHandlerImpl implements PersonenHandler {
    private final PersonenService service;
    private final PersonDTOMapper mapper;

    @Async
    public void handleSave(PersonDTO dto) throws PersonenServiceException {
        try {
            // Summ der Async - methoden in console;
            // foo
            // bar
            // summe in console;

            service.speichereOderAendere(mapper.convert(dto));
            // Success-Event
        } catch (PersonenServiceException e) {
            // Failure Event
            throw e;
        }
    }

    @Async
    public void handleDelete(String id) throws PersonenServiceException {
        try {
            service.loesche(id);
            // Success-Event
        } catch (PersonenServiceException e) {
            // Failure Event
            throw e;
        }
    }

}
