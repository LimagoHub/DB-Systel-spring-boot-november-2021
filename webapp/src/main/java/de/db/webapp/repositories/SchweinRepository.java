package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.SchweinEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SchweinRepository extends CrudRepository<SchweinEntity, String> {

    @Query("update SchweinEntity s set s.gewicht=:gewicht")
    void neuesGewicht(int gewicht);
}
