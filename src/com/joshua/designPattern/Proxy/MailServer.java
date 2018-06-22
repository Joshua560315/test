package com.joshua.designPattern.Proxy;

/**
 * Created by bmk on 18-3-21.
 */
public class MailServer implements Server{

    private String message;


    void sendMail(String message){
        System.out.println("发送邮件成功");
        saveMessage(message);
    }

    void saveMessage(String message) {
        System.out.println("保存邮件到邮件服务器");
    }
}
