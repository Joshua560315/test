package com.joshua.designPattern.Bridge;

import com.joshua.designPattern.Adapter.PlatformType;

/**
 * Created by bmk on 18-3-16.
 */
public class Ps4GameAPI implements GameAPI {
    @Override
    public void playGame(Game game) {
        if (game.type.equals(PlatformType.PS4)) {
            game.run();
        } else {
            System.out.println("本主机不支持此款游戏");
        }
    }
}
