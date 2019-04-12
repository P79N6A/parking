package com.zoeeasy.cloud.pds.enums;

/**
 * 富尚电量状态枚举
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
public enum BatteryTypeEnum {
    //0 正常,1 欠压,2 即将耗尽
    NORMAL(0, "正常"),
    UNDERVOLTAGE(1, "欠压"),
    WILL_DEPLETE(2, "即将耗尽");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    BatteryTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static BatteryTypeEnum parse(Integer value) {
        if (value != null) {
            BatteryTypeEnum[] array = values();
            for (BatteryTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
