package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 车牌类型枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PlateNumberStyleEnum {
    /**
     * 无类型
     */
    NONE(0, "无类型"),

    /**
     * 92式民用车
     */
    CIVIL_CAR(1, "92式民用车"),

    /**
     * 警用车
     */
    POLICE_CAR(2, "警用车"),

    /**
     * 上下军车
     */
    UPDOWMARMY_CAR(3, "上下军车"),

    /**
     * 92式武警车
     */
    ARMYPOLICE_CAR(4, "92式武警车"),

    /**
     * 左右军车车牌类型(一行结构)
     */
    LEFTRIGHTARMY_CAR(5, "左右军车车牌类型(一行结构)"),

    /**
     * 02式个性化车
     */
    INDIVIDUATION_CAR(7, "02式个性化车"),

    /**
     * 黄色双行尾牌
     */
    YELLOWDOUBLETAIL_CAR(8, "黄色双行尾牌"),

    /**
     * 04式新军车
     */
    NEWARMY_CAR(9, "04式新军车"),

    /**
     * 使馆车
     */
    DIPLOMATIC_CAR(10, "使馆车"),

    /**
     * 一行结构的新WJ车
     */
    A_ROW_WJ_CAR(11, "一行结构的新WJ车"),

    /**
     * 两行结构的新WJ车
     */
    TWO_ROW_WJ_CAR(12, "两行结构的新WJ车"),

    /**
     * 黄色1225农用车
     */
    YELLOW_1225_FARM_CAR(13, "黄色1225农用车"),

    /**
     * 绿色1225农用车
     */

    GREEN_FARM_CAR(14, "绿色1225农用车"),

    /**
     * 黄色1325农用车
     */

    YELLOW_1325_FARM_CAR(15, "黄色1325农用车"),

    /**
     * 摩托车
     */
    MOTO_CAR(16, "摩托车"),

    /**
     * 13式新武警总部一行车牌
     */

    NEW_ARMYPOLICE_AROW_CAR(17, "13式新武警总部一行车牌"),

    /**
     * 13式新武警总部两行车牌
     */
    NEW_ARMYPOLICE_TWOROW_CAR(18, "13式新武警总部两行车牌"),

    /**
     * 民航车牌类型
     */
    CIVIL_AVIATION_CAR(19, "民航车牌类型"),

    /**
     * 教练车
     */
    LEARNER_DRIVEN_CAR(100, "教练车"),

    /**
     * 临时行驶车
     */
    TEMPORARY_CAR(101, "临时行驶车"),

    /**
     * 挂车
     */
    TRAILER_CAR(102, "挂车"),

    /**
     * 领馆汽车
     */
    CONSULATE_CAR(103, "领馆汽车"),

    /**
     * 港澳入出车
     */
    HONGKONG_MACAO_CAR(104, "港澳入出车"),

    /**
     * 临时入境车
     */
    TEMPORARY_ENTRY_CAR(105, "临时入境车"),;

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
    public static PlateNumberStyleEnum parse(Integer value) {
        if (value != null) {
            PlateNumberStyleEnum[] array = values();
            for (PlateNumberStyleEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
