package com.zhuyitech.parking.common.enums;


/**
 * 车牌类型
 *
 * @author walkman
 */
public enum LicensePlateTypeEnum {

    /**
     * 大型汽车号牌
     */
    DAXING("01", "大型汽车号牌"),

    /**
     * 小型汽车号牌
     */
    XIAOXING("02", "小型汽车号牌"),

    /**
     * 使馆汽车号牌
     */
    SHIGUAN("03", "使馆汽车号牌"),

    /**
     * 领馆汽车号牌
     */
    LINGGUAN("04", "领馆汽车号牌"),

    /**
     * 境外汽车号牌
     */
    JINGWAI("05", "境外汽车号牌"),

    /**
     * 外籍汽车号牌
     */
    WAIJI("06", "外籍汽车号牌"),

    /**
     * 两、三轮摩托车号牌
     */
    SANLUN("07", "两、三轮摩托车号牌"),

    /**
     * 轻便摩托车号牌
     */
    QINGBIAN("08", "轻便摩托车号牌"),

    /**
     * 使馆摩托车号牌
     */
    SHIGUAN_MOTO("09", "使馆摩托车号牌"),

    /**
     * 领馆摩托车号牌
     */
    LINGGUAN_MOTO("10", "领馆摩托车号牌"),

    /**
     * 境外摩托车号牌
     */
    JINGWAI_MOTO("11", "境外摩托车号牌"),

    /**
     * 外籍摩托车号牌
     */
    WAIJI_MOTO("12", "外籍摩托车号牌"),

    /**
     * 农用运输车号牌
     */
    NONGYONG("13", "农用运输车号牌"),
    /**
     * 拖拉机号牌
     */
    TUOLAJI("14", "拖拉机号牌"),

    /**
     * 挂车号牌
     */
    GUACHE("15", "挂车号牌"),

    /**
     * 教练汽车号牌
     */
    JIAOLIAN("16", "教练汽车号牌"),

    /**
     * 教练摩托车号牌
     */
    JIAOLIAN_MOTO("17", "教练摩托车号牌"),

    /**
     * 试验汽车号牌
     */
    SHIYAN("18", "试验汽车号牌"),

    /**
     * 试验摩托车号牌
     */
    SHIYAN_MOTO("19", "试验摩托车号牌"),

    /**
     * 临时入境汽车号牌
     */
    LINSHIRUJING("20", "临时入境汽车号牌"),

    /**
     * 临时入境摩托车号牌
     */
    LINSHIRUJING_MOTO("21", "临时入境摩托车号牌"),

    /**
     * 临时行驶车号牌
     */
    LINSHI("22", "临时行驶车号牌"),

    /**
     * 警用汽车号牌
     */
    JINGYONG("23", "警用汽车号牌"),

    /**
     * 警用摩托号牌
     */
    JINGYONG_MOTO("24", "警用摩托号牌"),;

    private String code;

    private String name;

    private LicensePlateTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getValue() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
