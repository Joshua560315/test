package com.joshua.MultiThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by bmk on 18-4-17.
 */
public class Test {

    private Holder holder;

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> test.holder = new Holder(23)).start();
        new Thread(() -> {
            test.holder = new Holder(22);
            test.holder.assertSanity();
        }).start();
    }
}
