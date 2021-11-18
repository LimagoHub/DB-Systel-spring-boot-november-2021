package de.threads;

public class MyThread extends Object implements Runnable{

    private final String message;

    public MyThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(message);
                Thread.sleep((long)(Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
