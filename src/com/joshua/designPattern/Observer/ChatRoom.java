package com.joshua.designPattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmk on 18-4-10.
 */
public class ChatRoom {
    private List<Observer> users = new ArrayList<>();

    public void join(Observer user) {
        users.add(user);
    }

    public void sendMsg(User user, String msg){
        if (!users.contains(user)){
            System.out.println("Please join this chatRoom");
        } else {
            notifyAll(user.getName()+":" +msg);
        }
    }

    public void notifyAll(String msg){
        users.stream().forEach(user->user.update(msg));
    }
}
