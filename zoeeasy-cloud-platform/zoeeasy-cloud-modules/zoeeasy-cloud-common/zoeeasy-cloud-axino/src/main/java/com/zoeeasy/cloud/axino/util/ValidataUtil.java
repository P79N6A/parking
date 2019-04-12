package com.zoeeasy.cloud.axino.util;


/**
 * 校验类
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class ValidataUtil {

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

}
