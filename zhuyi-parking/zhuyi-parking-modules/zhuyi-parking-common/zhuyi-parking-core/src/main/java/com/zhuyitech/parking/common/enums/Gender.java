package com.zhuyitech.parking.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 性别
 *
 * @author walkman
 */
public enum Gender {
    /**
     * 未知
     */
    Other(0, "未知"),
    /**
     * 男
     */
    Male(1, "男"),
    /**
     * 女
     */
    Female(2, "女");

    @Getter
    @Setter
    private Integer value;

    @Getter
    @Setter
    private String name;

    private Gender(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
