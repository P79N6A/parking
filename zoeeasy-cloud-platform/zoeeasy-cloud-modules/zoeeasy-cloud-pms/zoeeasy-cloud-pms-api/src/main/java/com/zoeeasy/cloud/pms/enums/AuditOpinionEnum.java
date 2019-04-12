package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AuditOpinionEnum {

    PASS(2, "通过"),

    NOT_PASS(3, "驳回"),

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
    public static AuditOpinionEnum parse(Integer value) {
        if (value != null) {
            AuditOpinionEnum[] array = values();
            for (AuditOpinionEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
