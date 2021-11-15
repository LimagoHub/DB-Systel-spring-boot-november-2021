package de.math;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("secure")
public class CalculatorSecure implements Calculator{

    public final Calculator calculator;

    public CalculatorSecure(@Qualifier("logger") Calculator calculator) {
        this.calculator = calculator;
    }

    public double add(double a, double b) {
        System.out.println("Du kommst hier rein");
        return calculator.add(a, b);
    }

    public double sub(double a, double b) {
        return calculator.sub(a, b);
    }
}
