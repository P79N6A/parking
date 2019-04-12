package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CloudResultTypeEnum {
    //1：成功，-1：失败
    SUCCESS(1, "成功"),
    FAILED(-1, "失败"),;
    /**
     * 值
     */
    @Getter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static CloudResultTypeEnum parse(Integer value) {
        if (value != null) {
            CloudResultTypeEnum[] array = values();
            for (CloudResultTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
