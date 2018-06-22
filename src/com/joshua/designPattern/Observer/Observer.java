package com.joshua.designPattern.Observer;

/**
 * Created by bmk on 18-4-10.
 */
public interface Observer {
    default void update(String msg){
        System.out.println(msg);
    }
}
