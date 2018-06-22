package com.joshua.designPattern.Observer;

/**
 * Created by bmk on 18-4-10.
 */
public class Test {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        for(int i = 0; i <3; i++){
            User user = new User("User"+i);
            chatRoom.join(user);
            user.sendMessage("hello",chatRoom);
        }
        User user = new User("User");
        user.sendMessage("hello", chatRoom);
    }
}
