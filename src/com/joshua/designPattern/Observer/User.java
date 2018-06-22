package com.joshua.designPattern.Observer;

import lombok.Data;

/**
 * Created by bmk on 18-4-10.
 */
@Data
public class User implements Observer {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String msg, ChatRoom chatRoom){
        if (chatRoom== null){
            System.out.println("Not join a chatRoom");
        } else {
            chatRoom.sendMsg(this,msg);
        }
    }
}
