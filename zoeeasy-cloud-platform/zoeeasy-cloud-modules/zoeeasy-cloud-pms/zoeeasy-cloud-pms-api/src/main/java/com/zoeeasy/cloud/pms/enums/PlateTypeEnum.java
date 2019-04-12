package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PlateTypeEnum {

    OTHER(0, "其他"),

    CIVIL_CAR(1, "92式民用车"),

    DIPLOMATIC_MISSION_CAR(2, "大型汽车"),

    INSTRUCTIONAL_CAR(3, "教练车"),

    TEMPORARY_CAR(4, "临时行驶车"),

    TRAILER(5, "挂车"),

    CONSULATE_CAR(6, "领馆汽车"),

    HK_AND_MACAO_CAR(7, "港澳入出车"),

    TEMPORARY_IMMIGRATION_CAR(8, "临时入境车"),

    ONE_WJ_CAR(9, "一行结构的新WJ车"),

    TWO_WJ_CAR(10, "两行结构的新WJ车"),

    YELLOW_1225(11, "黄色1225农用车"),

    GREEN_1325(12, "绿色1325农用车"),

    YELLOW_1325(13, "黄色1325农用车"),

    MOTORCYCLE(14, "摩托车"),

    POLICE_CAR(15, "警用车"),

    UP_AND_DOWN_MILITARY_VEHICLE(16, "上下军车"),

    ARMED_POLICE_CAR(17, "92式武警车"),

    LEFT_AND_RIGHT_MILITARY_VEHICLE(18, "左右军车车牌类型(一行结构)"),

    INDIVIDUALITY_CAR(19, "02式个性化车"),

    YELLOW_TWIN_ROW(20, "黄色双行尾牌"),

    NEW_MILITARY_VEHICLE(21, "04式新军车"),

    ARMED_POLICE_ONE_CAR(22, "13式新武警总部一行车牌"),

    ARMED_POLICE_TWO_CAR(22, "13式新武警总部两行车牌"),

    CIVIL_AVIATION_PLATE(23, "民航车牌类型"),

    NEW_ENERGY(24, "新能源车牌"),

    /**
     * 不区分
     */
    ALl(30, "不区分");

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
    public static PlateTypeEnum parse(Integer value) {
        if (value != null) {
            PlateTypeEnum[] array = values();
            for (PlateTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
