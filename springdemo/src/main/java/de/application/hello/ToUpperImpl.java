package de.application.hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
public class ToUpperImpl implements Translator{
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
