package com.zoeeasy.cloud.axino.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sdk.jss.com.cn
 *
 * @author open.jss.com
 * @version 2.0
 * @since jdk1.6
 */
public class MD5Util {

    /**
     * 将字符串转换成md5
     *
     * @param source
     * @return
     */
    public static String toMD5(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
