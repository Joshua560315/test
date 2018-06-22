package com.joshua.designPattern.Proxy;

/**
 * Created by bmk on 18-3-21.
 */
public interface Server {
    default void connect(){
        System.out.println("已链接");
    }
}
