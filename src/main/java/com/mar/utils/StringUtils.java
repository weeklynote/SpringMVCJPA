package com.mar.utils;

/**
 * @Author: 刘劲
 * @Date: 2020/4/22 13:55
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }
}
