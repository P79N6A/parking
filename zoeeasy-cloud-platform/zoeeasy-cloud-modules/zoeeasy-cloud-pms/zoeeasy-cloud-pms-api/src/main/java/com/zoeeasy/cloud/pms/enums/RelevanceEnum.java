package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2018/10/29.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum RelevanceEnum {

    NO(0, "未关联"),

    YES(1, "已关联"),

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
    public static RelevanceEnum parse(Integer value) {
        if (value != null) {
            RelevanceEnum[] array = values();
            for (RelevanceEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }


}
