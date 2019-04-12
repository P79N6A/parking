package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/27.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PictureTypeEnum {

    DEFAULT(1, "默认图片"),
    NAVIGATION(2, "楼层导航图片"),

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
    public static PictureTypeEnum parse(Integer value) {
        if (value != null) {
            PictureTypeEnum[] array = values();
            for (PictureTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
