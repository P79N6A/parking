package com.zhuyitech.parking.common.enums;

/**
 * 准驾车型
 *
 * @author walkman
 */
public enum DriverClassEnum {

    /**
     * A1
     */
    A1("A1", "大型客车"),

    /**
     * A2
     */
    A2("A2", "牵引车"),

    /**
     * A3
     */
    A3("A3", "城市公交车"),

    /**
     * B1
     */
    B1("B1", "中型客车"),

    /**
     * B2
     */
    B2("B2", "大型货车"),

    /**
     * C1
     */
    C1("C1", "小型汽车"),

    /**
     * C2
     */
    C2("C2", "小型自动挡汽车"),

    /**
     * C3
     */
    C3("C3", "低速载货汽车"),

    /**
     * C4
     */
    C4("C4", "三轮汽车"),

    /**
     * C5
     */
    C5("C5", "残疾人专用小型自动挡载客汽车"),

    /**
     * D
     */
    D("D", "普通三轮摩托车"),

    /**
     * E
     */
    E("E", "普通二轮摩托车"),

    /**
     * F
     */
    F("F", "轻便摩托车"),

    /**
     * M
     */
    M("M", "轮式自行机械车"),

    /**
     * N
     */
    N("N", "无轨电车"),

    /**
     * P
     */
    P("P", "有轨电车"),
    ;

    private String code;
    private String desc;

    private DriverClassEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
