package com.zoeeasy.cloud.order.enums;

import lombok.Getter;

/**
 * 第三方平台停车账单同步状态
 *
 * @author walkman
 * @date 2018/1/26
 */
public enum ThirdOrderSyncStatusEnum {

    /**
     * 未创建
     */

    UNCREATED(0, "未创建"),

    /**
     * 已创建
     */
    CREATED(1, "已创建"),

    /**
     * 已确认
     */

    CONFIRM(2, "已确认"),

    /**
     * 创建中
     */

    CREATING(3, "创建中");

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

    ThirdOrderSyncStatusEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ThirdOrderSyncStatusEnum parse(Integer value) {
        if (value != null) {
            ThirdOrderSyncStatusEnum[] array = values();
            for (ThirdOrderSyncStatusEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }
}

