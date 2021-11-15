package de.application.hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
public class ToLowerImpl implements Translator{
    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
