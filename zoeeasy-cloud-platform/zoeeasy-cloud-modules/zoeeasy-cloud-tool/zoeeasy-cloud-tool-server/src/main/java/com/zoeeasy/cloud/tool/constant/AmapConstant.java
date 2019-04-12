package com.zoeeasy.cloud.tool.constant;

import com.scapegoat.infrastructure.config.FundamentalConfigProvider;

/**
 * 高德地图配置参数
 *
 * @author walkman
 * @date 2018/4/29
 */
public class AmapConstant {

    private AmapConstant() {
    }

    /**
     * 高德KEY
     */
    public static final String KEY = FundamentalConfigProvider.getString("amap.platform.key");
    /**
     *
     */
    public static final String FORMAT = "JSON";
    /**
     * 单次拉取Region表数据量
     */
    public static final Integer PAGE_SIZE = FundamentalConfigProvider.getInt("amap.region.pagesize");
    /**
     * 高德接口调用前缀
     */
    private static final String GUIDE_URL_PREFIX = FundamentalConfigProvider.getString("amap.url.prefix");
    /**
     * 获取天气信息
     */
    public static final String WEATHER_INFO_URL = GUIDE_URL_PREFIX + "/weather/weatherInfo";
    /**
     * 逆地理获取adcode
     */
    public static final String GEO_CODE_REGEO_URL = GUIDE_URL_PREFIX + "/geocode/regeo";
    /**
     * 坐标转换
     */
    public static final String COORDINATE_CONVERT_URL = GUIDE_URL_PREFIX + "/assistant/coordinate/convert";

}
