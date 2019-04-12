package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum OperateStatusEnum {

    APPLY_ON(1, "申请上架"),

    APPLY_OUT(2, "申请下架"),

    CAN_REPEAL(3, "可撤销申请"),

    CANNOT_REPEAL(4, "不可撤销申请"),

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
    public static OperateStatusEnum parse(Integer value) {
        if (value != null) {
            OperateStatusEnum[] array = values();
            for (OperateStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
