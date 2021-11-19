package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.Bar;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface BarRepository extends CrudRepository<Bar, String> {
}
