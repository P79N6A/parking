package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PacketTypeEnum {

    /**
     * 包月
     */
    PACKET_MONTH(1, "包月"),
    /**
     * 包年
     */
    PACKET_YEAR(2, "包年");
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
    public static PacketTypeEnum parse(Integer value) {
        if (value != null) {
            PacketTypeEnum[] array = values();
            for (PacketTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
