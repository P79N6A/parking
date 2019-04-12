package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum SpecialTypeEnum {

    WHITE_LIST(1, "白名单"),

    BLACK_LIST(2, "黑名单"),

    FIXED_CAR(3, "固定车"),

    VISITOR_CAR(4, "访客车"),

    PACKET_CAR(5, "包期车"),

    TEMPORTARY_CAR(6, "临时车"),

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
    public static SpecialTypeEnum parse(Integer value) {
        if (value != null) {
            SpecialTypeEnum[] array = values();
            for (SpecialTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
