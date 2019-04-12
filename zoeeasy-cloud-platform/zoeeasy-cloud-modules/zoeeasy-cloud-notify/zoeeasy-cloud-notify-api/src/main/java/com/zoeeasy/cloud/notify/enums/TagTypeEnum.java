package com.zoeeasy.cloud.notify.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@AllArgsConstructor
@NoArgsConstructor
public enum TagTypeEnum {
    PARKING_INFO_TAG(1, "停车场tag"),
    INSPECT_INFO_TAG(2, "巡检员tag"),
    COLLECT_INFO_TAG(3, "收费员tag"),
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
    public static TagTypeEnum parse(Integer value) {
        if (value != null) {
            TagTypeEnum[] array = values();
            for (TagTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
