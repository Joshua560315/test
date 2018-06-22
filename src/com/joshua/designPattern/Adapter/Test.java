package com.joshua.designPattern.Adapter;

/**
 * Created by bmk on 18-3-16.
 */
public class Test {
    public static void main(String[] args) {
        NSConsole ns = new NSConsole();
        ns.showName();
        ns.useApp("Zelda");
        ns.install("Zelda", Zelda.class);
        ns.useApp("Zelda");
        ns.install("Ps4Simulator", Ps4Simulator.class);
        ns.useApp("Ps4Simulator");
    }
}
