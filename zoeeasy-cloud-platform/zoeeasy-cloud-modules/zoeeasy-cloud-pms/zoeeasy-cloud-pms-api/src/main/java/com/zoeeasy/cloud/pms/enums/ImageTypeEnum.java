package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 停车图像类型枚举
 *
 * @author walkman
 * @date 2017-12-10
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ImageTypeEnum {

    /**
     * 1级全景图，2级车牌图
     */
    FULLIMAGE(1, "全景图"),

    PLATEIMAGE(2, "车牌图"),
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
    public static ImageTypeEnum parse(Integer value) {
        if (value != null) {
            ImageTypeEnum[] array = values();
            for (ImageTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
