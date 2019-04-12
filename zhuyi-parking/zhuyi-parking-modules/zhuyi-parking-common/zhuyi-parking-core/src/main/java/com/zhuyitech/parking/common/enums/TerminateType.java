package com.zhuyitech.parking.common.enums;

/**
 * 终端类型枚举
 *
 * @author walkman
 */
public enum TerminateType {

    /**
     * 未知
     */
    OTHER(0, "未知"),
    /**
     * ANDROID
     */
    ANDROID(1, "安卓"),
    /**
     * APPLE
     */
    APPLE(2, "苹果"),
    /**
     * H5
     */
    H5(3, "H5"),
    /**
     * WEB
     */
    WEB(4, "WEB"),;

    private Integer value;
    private String name;

    private TerminateType(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
