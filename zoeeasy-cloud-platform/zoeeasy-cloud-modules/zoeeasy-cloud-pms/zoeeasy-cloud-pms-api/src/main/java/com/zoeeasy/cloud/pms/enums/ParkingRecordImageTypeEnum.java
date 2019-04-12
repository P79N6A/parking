package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 停车图片文件类型
 *
 * @author AkeemSuper
 * @date 2018/10/25 0025
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingRecordImageTypeEnum {

    /**
     * 1  jpg
     */
    JPG(1, "jpg"),

    /**
     * 2 png
     */
    PNG(2, "png");
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
    public static ParkingRecordImageTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingRecordImageTypeEnum[] array = values();
            for (ParkingRecordImageTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应的枚举
     *
     * @param comment
     * @return
     */
    public static ParkingRecordImageTypeEnum getValue(String comment) {
        if (comment != null) {
            ParkingRecordImageTypeEnum[] array = values();
            for (ParkingRecordImageTypeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
