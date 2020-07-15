package com.alex.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by caozhennan on 2019/12/3 7:39 下午.
 */
public class Utils {
    public static <T> T json2Object(byte[] s, Class<T> targetClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(s, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
