package com.joshua.MultiThread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bmk on 18-4-16.
 */
public class FixedThreadPool {

    private static List<Integer> list = new ArrayList<>(10000);

    private static List<Integer> data = new ArrayList<>(10000);

    private static long startTime;

    public static void main(String[] args) {
        for (int i =0; i<10000; i++){
            data.add(i);
        }
        Collections.synchronizedCollection(list);
        startTime = System.currentTimeMillis();
        processWithThread();
//          processWithoutThread();
    }

    private static void processWithThread(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        FixedThreadPool threadPool = new FixedThreadPool();
        int threadSize = 5000;
        int threadTotal = data.size()%threadSize == 0? data.size()/threadSize :data.size()/threadSize+1;
        for (int i = 0; i<threadTotal;i++){
            pool.execute(threadPool.getThread(i*threadSize,threadSize));
        }
        pool.shutdown();
    }

    private static void processWithoutThread(){
        data.stream()
                .map(num -> num*3+4)
                .distinct()
                .forEach(num -> list.add(num));
        long end = System.currentTimeMillis();
        System.out.println(end-startTime);
    }

    public Runnable getThread(int index, int size){
        return new MyRunnable(index, size);
    }

    public class MyRunnable implements Runnable{
        private int start;

        private int size;

        public MyRunnable(int start, int size) {
            this.start = start;
            this.size = size;
        }

        @Override
        public void run() {
            data.stream()
                    .skip(start)
                    .limit(size)
                    .map(num -> num*3+4)
                    .distinct()
                    .forEach(num -> list.add(num));
            if (data.size()-start <= size){
                long endUp = System.currentTimeMillis();
                long usedTime = endUp-startTime;
                System.out.println("usedTime:"+usedTime);
            }
        }
    }
}
