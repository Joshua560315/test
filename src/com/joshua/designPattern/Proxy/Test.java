package com.joshua.designPattern.Proxy;

/**
 * Created by bmk on 18-3-21.
 */
public class Test {
    public static void main(String[] args) {
        MailServer mailServer = new MailServer();
        mailServer.connect();
        mailServer.sendMail("你好");
        MailProxy mailProxy = new MailProxy(mailServer);
        mailProxy.connect();
        mailProxy.sendMail("你好");
    }
}
