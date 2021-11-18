package de.threads;

public class ThreadTest {
    public static void main(String[] args) throws Exception{
        MyThread hund = new MyThread("wau");
        MyThread katze = new MyThread("miau");
        MyThread maus = new MyThread("piep");

        Thread t1 = new Thread(hund);
        Thread t2 = new Thread(katze);
        Thread t3 = new Thread(maus);

        t1.start();
        t2.start();
        t2.join();
        t3.start();
    }
}
