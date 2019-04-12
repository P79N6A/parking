package com.zoeeasy.cloud.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum SysResultEnum {

    //操作日志
    OPERATION_LOG_TITLE_EMPTY(10600, "操作日志标题为空"),
    OPERATION_LOG_NOT_FOUND(10601, "操作日志不存在"),

    //参数
    PARAMKEY_EXIST(10700, "参数名已存在"),
    PARAMVALUE_EXIST(10701, "参数值已存在"),
    PARAM_NOT_EXIST(10702, "参数不存在");

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
    public static SysResultEnum parse(Integer value) {
        if (value != null) {
            SysResultEnum[] array = values();
            for (SysResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
