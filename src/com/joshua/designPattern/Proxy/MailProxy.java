package com.joshua.designPattern.Proxy;

/**
 * Created by bmk on 18-3-21.
 */
public class MailProxy implements Server {
    MailServer mailServer;

    public MailProxy(MailServer mailServer) {
        this.mailServer = mailServer;
    }

    @Override
    public void connect() {
        System.out.println("连接到代理服务器");
    }

    void sendMail(String message){
        mailServer.sendMail(message);
    }
}
