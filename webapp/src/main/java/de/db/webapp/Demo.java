package de.db.webapp;


import de.db.webapp.repositories.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Demo {

    private final  Translator translator;

    @Value("${demo.message}")
    private String message;

    public Demo(Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Hello World"));
    }

    @PostConstruct
    public void go() {
        System.out.println(translator.translate(message));
    }

    public static interface PersonenRepository extends CrudRepository<PersonEntity, String> {

        Iterable<PersonEntity> findByVorname(String vorname);
        Iterable<PersonEntity> herbert();

        @Query("from PersonEntity")
        Iterable<PersonEntity> fritz();

        @Query("update PersonEntity set vorname=:vorname") // Patch
        void updateVorname(String vorname);
    }
}