package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据源类型枚举
 *
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PassDataTypeEnum {
    OTHER(0, "未知"),
    CLIENT(1, "客户端"),
    MAGNETIC(2, "地磁"),
    VIDEO(3, "视频"),
    ARTIFICIAL(4, "人工添加"),
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
    public static PassDataTypeEnum parse(Integer value) {
        if (value != null) {
            PassDataTypeEnum[] array = values();
            for (PassDataTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
