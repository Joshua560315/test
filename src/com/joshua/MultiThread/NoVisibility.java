package com.joshua.MultiThread;

/**
 * Created by bmk on 18-5-3.
 */
public class NoVisibility {
    private static boolean ready = false;
    private static int number = 0;

    public static void main(String[] args) {
        NoVisibility noVisibility = new NoVisibility();
        noVisibility.printThis();
        new Thread(() -> {
            while (!ready) {
                System.out.println(number);
                Thread.yield();
            }
            System.out.println(number);
        }).start();
        Thread t1 = new Thread(() -> {
            number = 42;
            ready = true;
        });
        try {
            Thread.sleep(300);
            t1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printThis() {
        System.out.println(NoVisibility.this);
    }
}
