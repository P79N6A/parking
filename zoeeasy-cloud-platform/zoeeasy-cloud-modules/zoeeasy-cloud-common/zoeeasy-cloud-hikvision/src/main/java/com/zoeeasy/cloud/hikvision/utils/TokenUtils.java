package com.zoeeasy.cloud.hikvision.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Description 获取hikvision的token
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@Slf4j
public class TokenUtils {

    private TokenUtils() {

    }

    public static String createToken(Map<String, Object> map, String secret) {
        SortedMap<String, Object> sortedMap = new TreeMap(map);
        StringBuilder toSign = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                toSign.append(key).append("=").append(String.valueOf(value)).append("&");
            }
        }
        toSign.append("secret=").append(secret);
        log.info("[createToken] -- 进行MD5加密之前的参数：{}", toSign);
        return DigestUtils.md5Hex(toSign.toString().getBytes()).toUpperCase();
    }

}
