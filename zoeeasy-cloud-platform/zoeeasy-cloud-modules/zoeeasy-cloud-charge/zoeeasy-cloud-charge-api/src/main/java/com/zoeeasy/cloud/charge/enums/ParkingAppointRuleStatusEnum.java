package com.zoeeasy.cloud.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 预约规则关联停车场的状态枚举
 * @Date: 2018/06/06 0001
 * @author: zwq
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ParkingAppointRuleStatusEnum {

    OUT_LINE(1, "未上线"),
    LING_ING(2, "上线中"),
    ON_LINE(3, "已上线"),
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
    public static ParkingAppointRuleStatusEnum parse(Integer value) {
        if (value != null) {
            ParkingAppointRuleStatusEnum[] array = values();
            for (ParkingAppointRuleStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
