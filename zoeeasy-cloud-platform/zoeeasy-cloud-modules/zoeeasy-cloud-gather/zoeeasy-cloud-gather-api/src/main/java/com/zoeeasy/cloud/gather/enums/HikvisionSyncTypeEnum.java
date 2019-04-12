package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 海康过车数据同步类型
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum HikvisionSyncTypeEnum {

    /**
     * 同步
     */
    SYNC(1, "同步"),

    /**
     * 校对
     */
    COLLATE(2, "校对"),

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
    public static HikvisionSyncTypeEnum parse(Integer value) {
        if (value != null) {
            HikvisionSyncTypeEnum[] array = values();
            for (HikvisionSyncTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
