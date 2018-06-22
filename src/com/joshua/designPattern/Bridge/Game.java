package com.joshua.designPattern.Bridge;

import com.joshua.designPattern.Adapter.PlatformType;

/**
 * Created by bmk on 18-3-16.
 */
public class Game {
    public PlatformType type;
    public String name;

    public Game(PlatformType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void  run(){
        System.out.println("启动"+this.name+",这是一款"+this.type.name()+"游戏");
    }
}
