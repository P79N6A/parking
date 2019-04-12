package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 * @author
 * @since
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingStatusEnum {

    NOT_ON_LINE(0, "下线"),

    ON_LINE(1, "在线"),

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
    public static ParkingStatusEnum parse(Integer value) {
        if (value != null) {
            ParkingStatusEnum[] array = values();
            for (ParkingStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
