package com.joshua.designPattern.Builder.AbstractClass;

import com.joshua.designPattern.Builder.bean.Bottle;
import com.joshua.designPattern.Builder.Interface.Food;
import com.joshua.designPattern.Builder.Interface.Packing;
import com.joshua.designPattern.factory.ReflectFactory;

/**
 * Created by bmk on 18-3-15.
 */
public abstract class Drink implements Food {
    @Override
    public Packing packing() {
        return ReflectFactory.getInstance(Bottle.class);
    }
}
