package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AllParkingEnum {

    NO(0, "否"),

    YES(1, "是"),

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
    public static AllParkingEnum parse(Integer value) {
        if (value != null) {
            AllParkingEnum[] array = values();
            for (AllParkingEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
