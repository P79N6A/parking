package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 业务操作类型
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessType {

    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 授权
     */
    GRANT(4, "授权"),

    /**
     * 导出
     */
    EXPORT(5, "导出"),

    /**
     * 导入
     */
    IMPORT(6, "导入"),

    /**
     * 强退
     */
    FORCE(7, "强退"),

    /**
     * 生成代码
     */
    GENCODE(8, "生成代码"),

    /**
     * 清空
     */
    CLEAN(9, "清空");

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
    public static BusinessType parse(Integer value) {
        if (value != null) {
            BusinessType[] array = values();
            for (BusinessType each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
