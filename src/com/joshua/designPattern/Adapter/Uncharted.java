package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class Uncharted extends App {
    @Override
    void run() {
        System.out.println("Play ps4 game:Uncharted");
    }

    @Override
    PlatformType type() {
        return PlatformType.PS4;
    }
}
