package com.zoeeasy.cloud.pds.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回结果枚举
 *
 * @author lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MagneticDetectorEnum {
    //设备
    MAGNETIC_DETECTOR_CODE_EMPTY(1010, "设备编号为空"),
    MAGNETIC_DETECTOR_EXISTS(1011, "设备已经存在"),
    MAGNETIC_DETECTOR_ID_EMPTY(1012, "设备ID为空"),
    MAGNETIC_DETECTOR_NOT_FOUND(1013, "设备不存在"),
    PARKING_EMPTY(1016, "停车场不存在"),
    PARKING_LOT_EMPTY(1017, "泊位不存在"),
    MAGNETIC_DETECTOR_FILE_WRONG(1018, "文件名导入错误"),
    MAGNETIC_DETECTOR_PROVIDER_EMPTY(1019, "设备厂商为空"),
    MAGNETIC_DETECTOR_PARKING_ID_EMPTY(1020, "停车场ID为空"),
    MAGNETIC_DETECTOR_PARKING_LOT_ID_EMPTY(1021, "泊位ID为空"),
    MAGNETIC_DETECTOR_SUPERVISOR_FOUND(1022, "设备管理器不存在"),
    PARKING_NAME_EMPTY(1023, "停车场名称为空"),
    PARKING_LOT_CODE_EMPTY(1024, "泊位号为空"),
    MAGNETIC_DETECTOR_CODE_WRONG(1025, "序列号输入有误"),
    PARKING_LOT_EMPTY_THIS_PARKING(1026, "停车场不存在此泊位"),
    NO_CONNECTION_PARKING_LOT(1027, "未关联泊位"),
    MAGNETIC_DETECTOR_NOT_REGISTERED(1028, "此设备未注册"),
    MAGNETIC_DETECTOR_REGISTERED(1028, "此设备已注册"),;
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
    public static MagneticDetectorEnum parse(Integer value) {
        if (value != null) {
            MagneticDetectorEnum[] array = values();
            for (MagneticDetectorEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
