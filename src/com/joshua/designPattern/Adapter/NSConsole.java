package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class NSConsole extends GameConsole {
    private GameAdapter simulator;

    @Override
    public void showName() {
        System.out.println("Use Nintendo Switch");
    }

    @Override
    boolean checkAvailable(App app) {
        if  (app.type().equals(PlatformType.NS)) {
            return true;
        }
        return false;
    }
}
