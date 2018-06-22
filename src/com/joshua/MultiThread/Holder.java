package com.joshua.MultiThread;

/**
 * Created by bmk on 18-5-7.
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("Statement error!");
        }
    }
}
