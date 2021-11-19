package de.db.webapp.application;


import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsynDemo {

    public Future<Long> foo() {
        long value = (long) (Math.random() * 2000);
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(value);
            return new AsyncResult<Long>(value);
        } catch (InterruptedException e) {
            //
        }

        return new AsyncResult<Long>(0L);
    }

    public Future<Long> bar() {
        long value = (long) (Math.random() * 2000);
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(value);
            return new AsyncResult<Long>(value);
        } catch (InterruptedException e) {
            //
        }

        return new AsyncResult<Long>(0L);
    }

}
