package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PutAwayStatusEnum {

    AWAIT_UP(1, "未上架"),

    ALREADY_UP(4, "已上架"),

    ALREADY_DOWN(7, "已下架"),

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
    public static PutAwayStatusEnum parse(Integer value) {
        if (value != null) {
            PutAwayStatusEnum[] array = values();
            for (PutAwayStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
