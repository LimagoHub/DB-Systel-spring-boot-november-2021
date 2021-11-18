package de.executordemo;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecuterDemo {

    public static void main(String[] args) throws Exception{
       new ExecuterDemo().go();
    }
    private void go() throws Exception{
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        ExecutorService service = Executors.newFixedThreadPool(availableProcessors);

//        for (int i = 0; i < availableProcessors * 2; i++) {
//            service.execute(this::tueWas);
//        }

        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < availableProcessors; i++) {
            futures.add(service.submit(new MyCallable()));
        }

        service.shutdown();

        futures.stream().parallel().mapToLong(this::convert).forEach(System.out::println);

        //service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("Fertisch");
    }

    private void tueWas() {
        try {
            Thread.sleep((long)(Math.random()*1000));
            System.out.println(Thread.currentThread().getId());
        } catch (InterruptedException e) {            e.printStackTrace();
        }
    }

    private long convert(Future<Long> f) {
        try {
            return f.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    class MyCallable implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long value = (long)(Math.random()*1000);
            Thread.sleep(value);
            System.out.println(Thread.currentThread().getId());
            return value;
        }
    }
}
