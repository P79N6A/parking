package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/26.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum LotTypeEnum {

    COMMUNITY(1, "小区停车场"),
    BUSINESS(2, "商圈停车场"),
    ROAD(3, "路面停车场"),
    GARDEN(4, "园区停车场"),
    OFFICE(5, "写字楼停车场"),
    PRIVATE(6, "私人停车场"),

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
    public static LotTypeEnum parse(Integer value) {
        if (value != null) {
            LotTypeEnum[] array = values();
            for (LotTypeEnum each : array) {
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
    public static LotTypeEnum getValue(String comment) {
        if (comment != null) {
            LotTypeEnum[] array = values();
            for (LotTypeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
