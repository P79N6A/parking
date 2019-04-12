package com.zhuyitech.parking.pay.wechat.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <pre>
 *     签名相关工具类
 * </pre>
 * @author walkman
 * @date 2017-07-10-15:14
 */
public class SignUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 微信公众号支付签名算法
     *
     * @param params        需要进行签名的参数
     * @param signKey       商户签名Key
     * @return
     */
    public static String createSign(Map<String, String> params, String signKey) {
        // 对签名参数进行树形排序
        SortedMap<String, String> sortedMap = new TreeMap<>(params);

        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            if (StringUtils.isNotBlank(value) && !"sign".equals(key) && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }
        toSign.append("key=").append(signKey);
        LOGGER.info("[createSign] -- 进行MD5加密之前的参数：{}", toSign.toString());
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }

    /**
     * 校验签名是否正确
     *
     * @param params  需要校验的参数Map
     * @param signKey 校验的签名Key
     * @return true - 签名校验成功，false - 签名校验失败
     */
    public static boolean checkSign(Map<String, String> params, String signKey) {
        String sign = createSign(params, signKey);
        LOGGER.info("[checkSign] -- 校验签名参数MD5签名后：{}", sign);
        return sign.equals(params.get("sigh"));
    }

}
