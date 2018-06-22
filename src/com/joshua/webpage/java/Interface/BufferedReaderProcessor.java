package com.joshua.webpage.java.Interface;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by linzl on 17-3-13.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    void process(BufferedReader br) throws IOException;
}
