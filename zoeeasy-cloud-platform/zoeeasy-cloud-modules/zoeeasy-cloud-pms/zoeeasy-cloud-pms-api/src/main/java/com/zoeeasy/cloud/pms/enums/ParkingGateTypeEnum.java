package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * created by kane on 2018/10/15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingGateTypeEnum {
    GATE_TYPE_IN(1, "入口"),
    GATE_TYPE_OUT(2, "出口"),
    GATE_TYPE_ENTRANCE(3, "出入口"),
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
     * 根据value获取对应枚举
     *
     * @param value
     * @return
     */
    public static ParkingGateTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingGateTypeEnum[] array = values();
            for (ParkingGateTypeEnum temp : array) {
                if (value.equals(temp.value)) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应枚举
     *
     * @param comment
     * @return
     */
    public static ParkingGateTypeEnum getValue(String comment) {
        if (comment != null) {
            ParkingGateTypeEnum[] array = values();
            for (ParkingGateTypeEnum temp : array) {
                if (comment.equals(temp.comment)) {
                    return temp;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }



}
