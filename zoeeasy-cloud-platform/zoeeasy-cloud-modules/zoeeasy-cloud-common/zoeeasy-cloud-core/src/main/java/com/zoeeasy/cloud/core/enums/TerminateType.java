package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 终端类型枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
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
    WEB(4, "WEB"),
    ;

    @Getter
    private Integer value;

    @Getter
    private String name;

    @Override
    public String toString() {
        return this.name;
    }

}
