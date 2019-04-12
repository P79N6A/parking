package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by zwq on 2018/12/05.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayLimitEnum {

    TEN(10, "10"),

    THIRTY(30, "30"),

    SIXTY(60, "60"),

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
    public static PayLimitEnum parse(Integer value) {
        if (value != null) {
            PayLimitEnum[] array = values();
            for (PayLimitEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
