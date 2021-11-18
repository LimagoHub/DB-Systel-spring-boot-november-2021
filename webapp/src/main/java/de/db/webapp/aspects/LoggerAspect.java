package de.db.webapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class LoggerAspect {



    @Before("PointCuts.restControllerMethodes()")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn(joinPoint.getSignature().getName());
    }
    @AfterReturning( value = "PointCuts.personenControllerMethodes()", returning = "retval")
    public void afterReturning(JoinPoint joinPoint,Object retval) {
        log.warn(retval.toString());
    }

    @AfterThrowing(value = "execution(public * de.db.webapp.controllers.PersonenQueryController.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Fehler {} ist aufgetreten", ex);
    }

    @Around(value = "execution(public * de.db.webapp.controllers.PersonenQueryController.*(..))")
    public Object zeitMessung(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Instant start = Instant.now();
        Object result = proceedingJoinPoint.proceed();
        Instant ende = Instant.now();
        Duration duration = Duration.between(start, ende);
        log.warn("Duration = {}", duration.toMillis());
        return result;
    }
}
