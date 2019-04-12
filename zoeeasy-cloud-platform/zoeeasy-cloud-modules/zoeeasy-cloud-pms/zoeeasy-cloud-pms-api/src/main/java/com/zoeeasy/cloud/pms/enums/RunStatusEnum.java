package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum RunStatusEnum {

    NOT_UP(1, "未上架"),

    UP_AUDIT_ING(2, "上架审核中"),

    UP_HANDLE_ING(3, "上架处理中"),

    ALREADY_UP(4, "已上架"),

    DOWN_AUDIT_ING(5, "下架审核中"),

    DOWN_HANDLE_ING(6, "下架处理中"),

    ALREADY_DOWN(7, "已下架"),

    UP_REJECT(8, "上架申请驳回"),

    DOWN_REJECT(9, "下架申请驳回"),

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
    public static RunStatusEnum parse(Integer value) {
        if (value != null) {
            RunStatusEnum[] array = values();
            for (RunStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
