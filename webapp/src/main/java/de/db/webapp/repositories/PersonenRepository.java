package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> , PersonenCustomRepository{

    Iterable<PersonEntity> findByVorname(String vorname);
    Iterable<PersonEntity> herbert();

    @Query("from PersonEntity")
    Iterable<PersonEntity> fritz();

    @Query("update PersonEntity set vorname=:vorname") // Patch
    void updateVorname(String vorname);

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> findeSpalten();

    @Query("select new de.db.webapp.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findeTinies();
}
