package com.joshua.designPattern.factory;

/**
 * Created by bmk on 18-3-14.
 */
public class ReflectFactory {
    public static <T> T getInstance(Class clazz) {
        T obj = null;
        try {
            obj = (T) Class.forName(clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
