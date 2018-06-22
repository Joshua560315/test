package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class Ps4Simulator extends App implements GameAdapter {
    private Ps4Console ps4Console = new Ps4Console();

    public Ps4Simulator() {
        this.ps4Console.install("Uncharted", Uncharted.class);
    }

    @Override
    public void playOtherGame(String gameName) {
        ps4Console.useApp(gameName);
    }


    @Override
    void run() {
        System.out.println("启动PS4模拟器...");
        this.ps4Console.useApp("Uncharted");
    }

    @Override
    PlatformType type() {
        return PlatformType.NS;
    }

    public void install(String appName, Class clazz){
        ps4Console.install(appName, clazz);
    }
}
