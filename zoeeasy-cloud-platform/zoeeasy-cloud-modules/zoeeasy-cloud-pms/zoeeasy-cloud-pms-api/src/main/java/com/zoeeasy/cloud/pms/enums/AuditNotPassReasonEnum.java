package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AuditNotPassReasonEnum {

    PARKING_AREA_INFO_ERROR(1, "停车场位置信息不准确"),

    PARKING_CHARGE_INFO_ERROR(2, "停车场收费信息不准确"),

    PARKING_LOT_COUNT_INFO_ERROR(3, "停车场泊位数量不准确"),

    PARKING_APPOINTMENT_INFO_ERROR(4, "停车场预约信息不准确"),

    OTHER(5, "其他"),

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
    public static AuditNotPassReasonEnum parse(Integer value) {
        if (value != null) {
            AuditNotPassReasonEnum[] array = values();
            for (AuditNotPassReasonEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
