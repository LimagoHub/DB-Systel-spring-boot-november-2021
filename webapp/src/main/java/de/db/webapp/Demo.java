package de.db.webapp;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Demo {

    private final  Translator translator;
    private final List<String> liste;


    @Value("${demo.message}")
    private String message;

    public Demo(Translator translator, @Qualifier("stadtLandFluss") List<String> liste) {
        this.translator = translator;
        this.liste = liste;
        System.out.println(liste);
        System.out.println(translator.translate("Hello World"));
    }

    @PostConstruct
    public void go() {

        System.out.println(translator.translate(message)
        );
    }


}
