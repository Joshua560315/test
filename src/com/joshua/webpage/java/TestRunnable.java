package com.joshua.webpage.java;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bmk on 17-9-12.
 */
public class TestRunnable {

    private static volatile int count = 0;

    private ArrayBlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);

    class Producer implements Runnable{
        @Override
        public void run() {
            int max = 0;
            try {
                while (max<20 && bq.size()<10){
                    bq.put(1);
                    count++;
                    System.out.println("Producer create,now goods:"+count);
                    max++;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    class Customer implements Runnable{
        @Override
        public void run() {
            try {
//                Thread.sleep(100);
                while (!bq.isEmpty()){
                    bq.take();
                    count--;
                    System.out.println("Customer use,now goods:"+count);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestRunnable foo = new TestRunnable();
        new Thread(foo.new Producer()).start();
        new Thread(foo.new Customer()).start();
        new Thread(foo.new Customer()).start();
        System.out.println(foo.bq.size());
    }
}
