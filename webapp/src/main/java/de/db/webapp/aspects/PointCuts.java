package de.db.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(public * de.db.webapp.controllers.PersonenQueryController.*(..))")
    public void personenControllerMethodes(){}

    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    public void restControllerMethodes() {}
}
