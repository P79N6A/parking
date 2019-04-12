package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ApplyStatusEnum {

    AWAIT(1, "待申请"),

    ALREADY(2, "已申请"),

    CANCEL(3, "已取消"),

    ;

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
    public static ApplyStatusEnum parse(Integer value) {
        if (value != null) {
            ApplyStatusEnum[] array = values();
            for (ApplyStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
