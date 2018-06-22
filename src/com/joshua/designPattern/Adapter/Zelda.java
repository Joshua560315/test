package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class Zelda extends App {
    @Override
    void run() {
        System.out.println("Play NS game:Zelda");
    }

    @Override
    PlatformType type() {
        return PlatformType.NS;
    }
}
