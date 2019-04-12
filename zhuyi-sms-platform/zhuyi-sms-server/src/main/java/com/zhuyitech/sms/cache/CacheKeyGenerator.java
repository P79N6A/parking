package com.zhuyitech.sms.cache;

import com.scapegoat.infrastructure.common.utils.TimeUtils;

import java.util.Date;

/**
 *
 */
public abstract class CacheKeyGenerator {

    private static final String SMS_VERIFY_CODE_PREFIX = "scapegoat:sms:verify_code";

    private static final String SMS_VERIFY_CODE_INFO_KEY_PREFIX = "scapegoat:sms:verify_code_info";

    /**
     * @param phone
     * @return
     */
    public static String generateVerifyCodeCacheKey(String clientId, String phone) {
        return new StringBuilder(SMS_VERIFY_CODE_PREFIX).append("_").append(clientId).
                append("_").append(phone).toString();
    }

    /**
     * 根据手机号、时间，组合成唯一的Key值
     *
     * @param phone
     * @returns
     */
    public static String generateVerifyCodeInfoCacheKey(String clientId, String phone) {
        String day = TimeUtils.formatTime(new Date(), TimeUtils.FORMAT_YYYYMMDD);
        return new StringBuilder(SMS_VERIFY_CODE_INFO_KEY_PREFIX)
                .append("_").append(day).append("_")
                .append(clientId).append("_")
                .append(phone).toString();
    }

}
