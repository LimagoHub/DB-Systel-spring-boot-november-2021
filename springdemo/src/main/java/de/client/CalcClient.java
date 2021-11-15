package de.client;

import de.math.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CalcClient {

    private final Calculator calculator;

    public CalcClient(@Qualifier("secure") Calculator calculator) {
        this.calculator = calculator;
    }

    @PostConstruct
    public void go() {

        System.out.println(calculator.add(3,4));
    }
}
