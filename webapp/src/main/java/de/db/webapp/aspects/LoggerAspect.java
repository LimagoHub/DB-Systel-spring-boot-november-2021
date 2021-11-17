package de.db.webapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Before(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn(joinPoint.getSignature().getName());
    }
    @AfterReturning( value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))", returning = "retval")
    public void afterReturning(JoinPoint joinPoint,Object retval) {
        log.warn(retval.toString());
    }

    @AfterThrowing(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Fehler {} ist aufgetreten", ex);
    }

    @Around(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))")
    public Object zeitMessung(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Object result = proceedingJoinPoint.proceed();

        return result;
    }
}
