package de.db.webapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class ToUpperImpl implements Translator{
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
