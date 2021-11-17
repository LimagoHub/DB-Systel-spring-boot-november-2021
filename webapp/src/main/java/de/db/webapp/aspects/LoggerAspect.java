package de.db.webapp.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    @Before(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))")
    public void logAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+ " wurde gerufen!!!!!!");
    }
}
