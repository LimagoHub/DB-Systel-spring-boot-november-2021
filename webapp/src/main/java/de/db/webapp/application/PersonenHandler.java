package de.db.webapp.application;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.services.PersonenServiceException;
import org.springframework.scheduling.annotation.Async;

public interface PersonenHandler {


    public void handleSave(PersonDTO dto) throws PersonenServiceException ;



    public void handleDelete(String id) throws PersonenServiceException ;
}
