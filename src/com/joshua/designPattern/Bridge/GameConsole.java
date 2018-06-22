package com.joshua.designPattern.Bridge;

/**
 * Created by bmk on 18-3-16.
 */
public abstract class GameConsole {
    GameAPI gameAPI;

    abstract void  play(Game game);
}
