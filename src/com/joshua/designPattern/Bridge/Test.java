package com.joshua.designPattern.Bridge;

import com.joshua.designPattern.Adapter.PlatformType;

/**
 * Created by bmk on 18-3-16.
 */
public class Test {
    public static void main(String[] args) {
        AdvancedConsole PS4 = new AdvancedConsole(new Ps4GameAPI());
        AdvancedConsole NS = new AdvancedConsole(new NsGameAPI());
        Game uncharted = new Game(PlatformType.PS4, "Uncharted");
        Game zelda = new Game(PlatformType.NS, "zelda");
        PS4.play(uncharted);
        PS4.play(zelda);
        NS.play(uncharted);
        NS.play(zelda);
    }
}
