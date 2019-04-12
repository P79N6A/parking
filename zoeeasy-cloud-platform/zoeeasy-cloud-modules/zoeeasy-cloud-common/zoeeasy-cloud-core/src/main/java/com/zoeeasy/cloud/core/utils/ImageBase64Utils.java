package com.zoeeasy.cloud.core.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
public class ImageBase64Utils {
    private ImageBase64Utils() {
    }

    //base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    //byte[]转base64
    public static String byte2Base64StringFun(byte[] b) {
        return Base64.encodeBase64String(b);
    }

    public static String getFileNameSuffix(String dataPrefix) {
        String suffix = null;
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrefix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrefix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrefix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrefix)) {
            suffix = ".png";
        }
        return suffix;
    }
}
