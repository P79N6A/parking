package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 车道类型枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum LaneStyleEnum {
    /**
     * 入口
     */
    ENTRANCE(0, "入口"),

    /**
     * 出口不收费
     */
    NO_CHARGE_FOR_EXPORT(1, "出口不收费"),

    /**
     * 出口岗亭收费
     */
    BOOTH_CHARGE_EXPORT(2, "出口岗亭收费"),

    /**
     * 出口中央收费
     */
    CENTRAL_CHARGES_EXPORT(3, "出口中央收费"),;

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
    public static LaneStyleEnum parse(Integer value) {
        if (value != null) {
            LaneStyleEnum[] array = values();
            for (LaneStyleEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
