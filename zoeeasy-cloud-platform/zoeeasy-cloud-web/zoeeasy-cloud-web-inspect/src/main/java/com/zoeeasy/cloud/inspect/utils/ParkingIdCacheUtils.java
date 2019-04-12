package com.zoeeasy.cloud.inspect.utils;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.shiro.redis.RedisConnector;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
public class ParkingIdCacheUtils {

    private ParkingIdCacheUtils() {
    }

    public static final String INSPECT_PARKING = "inspect_parking_id";

    /**
     * 生成inspectParkingIdKey
     *
     * @return
     */
    public static String generateVerifyCodeCacheKey(String userUuid) {
        return new StringBuilder(INSPECT_PARKING).append(":").append(userUuid).toString();
    }

    /**
     * 将parkingId放入redis
     *
     * @param key
     * @param parkingId
     */
    public static void setParkingIdToRedis(String key, Long parkingId) {
        RedisConnector.set(key, String.valueOf(parkingId));
    }

    public static String getParkingIdByRedis(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        String parkingId = RedisConnector.get(key);
        if (StringUtils.isEmpty(parkingId)) {
            return null;
        } else {
            return parkingId;
        }
    }
}
