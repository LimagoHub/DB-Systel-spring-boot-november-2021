package de.db.webapp;


import de.db.webapp.repositories.BarRepository;
import de.db.webapp.repositories.BarkeeperRepository;
import de.db.webapp.repositories.entities.Bar;
import de.db.webapp.repositories.entities.BarKeeper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DBClient {

    private final BarRepository repo;

    public DBClient(BarRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void foo() {
        Bar wunderbar = Bar.builder().id("4").name("Hubschraubar").build();

        BarKeeper keeper1 = BarKeeper.builder().id("5").keepername("Peter").build();
        BarKeeper keeper2 = BarKeeper.builder().id("6").keepername("Paul").build();
        BarKeeper keeper3 = BarKeeper.builder().id("7").keepername("Mary").build();

        wunderbar.addBarKeeper(keeper1);
        wunderbar.addBarKeeper(keeper2);
        wunderbar.addBarKeeper(keeper3);
        repo.save(wunderbar);

    }
}
