package com.joshua.webpage.java.bean;

/**
 * Created by bmk on 17-3-21.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return this.getName()+" in "+this.getCity();
    }
}
