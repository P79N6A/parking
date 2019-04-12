package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 车辆类型枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CarTypeEnum {
    
    /**
     * 其他
     */
    OTHER_CAR(0, "其他"),

    /**
     * 小汽车
     */
    SMALL_CAR(1, "小型汽车"),

    /**
     * 大汽车
     */
    BIG_CAR(2, "大型汽车"),

    /**
     * 不区分
     */
    ALL_TYPE(9, "不区分");

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
    public static CarTypeEnum parse(Integer value) {
        if (value != null) {
            CarTypeEnum[] array = values();
            for (CarTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
