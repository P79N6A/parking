package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum EffectedStatusEnum {

    NO_EFFECTED(1, "未生效"),

    YET_EFFECTED(2, "已生效"),

    LOSE_EFFECTED(3, "已失效"),

    ;

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
    public static EffectedStatusEnum parse(Integer value) {
        if (value != null) {
            EffectedStatusEnum[] array = values();
            for (EffectedStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
