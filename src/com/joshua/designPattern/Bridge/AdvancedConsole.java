package com.joshua.designPattern.Bridge;

/**
 * Created by bmk on 18-3-16.
 */
public class AdvancedConsole extends GameConsole {
    private GameAPI gameAPI;

    public AdvancedConsole(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
    }

    @Override
    void play(Game game) {
        this.gameAPI.playGame(game);
    }
}
