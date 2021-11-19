package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.BarKeeper;
import org.springframework.data.repository.CrudRepository;

public interface BarkeeperRepository extends CrudRepository<BarKeeper, String> {
}
