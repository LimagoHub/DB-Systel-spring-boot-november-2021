package de.db.webapp.services;

import de.db.webapp.services.models.Schwein;

import java.util.Optional;

public interface SchweineService {

    boolean speichere(Schwein schwein) throws SchweineServiceException ;
    Optional<Schwein> findeZuId(String id) throws SchweineServiceException ;
    Iterable<Schwein> findeAlle() throws SchweineServiceException ;
    boolean loesche(String id) throws SchweineServiceException ;
    boolean fuettere(String id) throws SchweineServiceException ;
}
