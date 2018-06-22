package com.joshua.designPattern.Adapter;

import com.joshua.designPattern.factory.ReflectFactory;

import java.util.HashMap;

/**
 * Created by bmk on 18-3-16.
 */
public abstract class GameConsole {
    private static HashMap<String, Class<? extends App>> appList = new HashMap<>();
    
    abstract void showName();

    void useApp(String appName){
        Class appClass = appList.get(appName);
        if (appClass == null) {
            System.out.println("尚未安装该软件");
            return;
        }
        App app = ReflectFactory.getInstance(appClass);
        if (!checkAvailable(app)){
            System.out.println("本主机不支持该类型的软件");
            return;
        }
        if (app != null) {
            app.run();
        } else {
            System.out.println("There is no such app available");
        }
    }

    abstract boolean checkAvailable(App app);

    void install(String appName, Class clazz){
        System.out.println("开始安装"+appName+"...");
        String superClass = clazz.getSuperclass().getName();
        if (!superClass.equals(App.class.getName())){
            System.out.println("不是有效的软件");
            return;
        }
        appList.put(appName, clazz);
        System.out.println(appName+"安装完毕");
    }
}
