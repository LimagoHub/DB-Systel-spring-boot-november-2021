package de.db.webapp.application;


import de.db.webapp.repositories.PersonenRepository;

import de.db.webapp.services.PersonenService;
import de.db.webapp.services.impl.PersonenServiceImpl;
import de.db.webapp.services.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class PersonenConfig {

    @Bean
    @Qualifier("antipathen")
    public Set<String> antipathen() {
        return Set.of("Attila", "Peter","Paul", "Mary");
    }
    @Bean
    @Qualifier("fruits")
    public Set<String> fruits() {
        return Set.of("Banana", "Strawberry","Cherry", "Raspberry");
    }

    @Bean
    public PersonenService createPersonenService(PersonenRepository repo, PersonMapper mapper, @Qualifier("antipathen") Set<String> antipathen) {
        return new PersonenServiceImpl(repo,mapper,antipathen);
    }
}
