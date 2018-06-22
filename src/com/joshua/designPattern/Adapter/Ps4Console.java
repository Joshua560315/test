package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class Ps4Console extends  GameConsole {
    @Override
    void showName() {
        System.out.println("Use playStation4");
    }

    @Override
    boolean checkAvailable(App app) {
        if (app.type().equals(PlatformType.PS4)){
            return true;
        }
        return false;
    }
}
