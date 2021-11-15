package de.application.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Hello {

    private String message = "hallo";

    private final Translator translator;

    @Autowired
    public Hello(@Qualifier("lower") final Translator translator) {
        this.translator = translator;
        System.out.println("Ctor with Parameter");
        System.out.println(translator.translate(message));
    }
//    public Hello() {
//
//        System.out.println("Ctor without Parameter");
//        System.out.println(message);
//    }

//    @Autowired
//    public void setTranslator(Translator translator) {
//        this.translator = translator;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void go() {
        System.out.println("init");
        System.out.println(translator.translate(message));
    }



    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
        System.out.println(translator.translate("Und Tschüss"));
    }
}
