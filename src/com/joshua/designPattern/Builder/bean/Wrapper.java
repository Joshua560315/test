package com.joshua.designPattern.Builder.bean;

import com.joshua.designPattern.Builder.Interface.Packing;

/**
 * Created by bmk on 18-3-15.
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "paper";
    }
}
