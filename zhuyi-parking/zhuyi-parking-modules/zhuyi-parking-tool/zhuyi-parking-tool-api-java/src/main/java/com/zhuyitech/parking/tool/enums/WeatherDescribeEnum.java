package com.zhuyitech.parking.tool.enums;


import com.scapegoat.infrastructure.common.utils.StringUtils;
import lombok.Getter;

/**
 * 天气状况枚举
 *
 * @author zwq
 * @date 2018-04-20
 */
public enum WeatherDescribeEnum {

    /**
     * 晴
     */
    QING("00", "晴"),

    /**
     * 多云
     */
    DUOYUN("01", "多云"),

    /**
     * 阴
     */
    YIN("02", "阴"),

    /**
     * 阵雨
     */
    ZHENYU("03", "阵雨"),

    /**
     * 雷阵雨
     */
    LEIZHENYU("04", "雷阵雨"),

    /**
     * 多云
     */
    LEIZHENYUBINGBAO("05", "雷阵雨并伴有冰雹"),

    /**
     * 雨夹雪
     */
    YUJIAXUE("06", "雨夹雪"),

    /**
     * 小雨
     */
    XIAOYU("07", "小雨"),

    /**
     * 中雨
     */
    ZHONGYU("08", "中雨"),

    /**
     * 大雨
     */
    DAYU("09", "大雨"),

    /**
     * 暴雨
     */
    BAOYU("10", "暴雨"),

    /**
     * 大暴雨
     */
    DABAOYU("11", "大暴雨"),

    /**
     * 特大暴雨
     */
    TEDABAOYU("12", "特大暴雨"),

    /**
     * 阵雪
     */
    ZHENXUE("13", "阵雪"),

    /**
     * 小雪
     */
    XIAOXUE("14", "小雪"),

    /**
     * 中雪
     */
    ZHONGXUE("15", "中雪"),

    /**
     * 大雪
     */
    DAXUE("16", "大雪"),

    /**
     * 暴雪
     */
    BAOXUE("17", "暴雪"),

    /**
     * 雾
     */
    WU("18", "雾"),

    /**
     * 冻雨
     */
    DONGYU("19", "冻雨"),

    /**
     * 沙尘暴
     */
    SHACHENBAO("20", "沙尘暴"),

    /**
     * 小雨-中雨
     */
    XIAOYU_ZHONGYU("21", "小雨-中雨"),

    /**
     * 中雨-大雨
     */
    ZHONGYU_DAYU("22", "中雨-大雨"),

    /**
     * 大雨-暴雨
     */
    DAYU_BAOYU("23", "大雨-暴雨"),

    /**
     * 暴雨-大暴雨
     */
    BAOYU_DABAOYU("24", "暴雨-大暴雨"),

    /**
     * 大暴雨-特大暴雨
     */
    DABAOYU_TEDABAOYU("25", "大暴雨-特大暴雨"),

    /**
     * 小雪-中雪
     */
    XIAOXUE_ZHONGXUE("26", "小雪-中雪"),

    /**
     * 中雪-大雪
     */
    ZHONGXUE_DAXUE("27", "中雪-大雪"),

    /**
     * 大雪-暴雪
     */
    DAXUE_BAOXUE("28", "大雪-暴雪"),

    /**
     * 浮尘
     */
    FUCHEN("29", "浮尘"),

    /**
     * 扬沙
     */
    YANGSHA("30", "扬沙"),

    /**
     * 强沙尘暴
     */
    QIANGSHACHENBAO("31", "强沙尘暴"),

    /**
     * 飑
     */
    BIAO("32", "飑"),

    /**
     * 龙卷风
     */
    LONGJUANFENG("33", "龙卷风"),

    /**
     * 弱高吹雪
     */
    RUOGAOCHUIXUE("34", "弱高吹雪"),

    /**
     * 轻雾
     */
    QINWU("35", "轻雾"),

    /**
     * 霾
     */
    MAI("53", "霾"),
    ;

    /**
     * 值
     */
    @Getter
    private String value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    WeatherDescribeEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值描述获取对应的枚举
     *
     * @param comment 枚举的描述
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static WeatherDescribeEnum parse(String comment) {
        if (StringUtils.isNotEmpty(comment)) {
            WeatherDescribeEnum[] array = values();
            for (WeatherDescribeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
