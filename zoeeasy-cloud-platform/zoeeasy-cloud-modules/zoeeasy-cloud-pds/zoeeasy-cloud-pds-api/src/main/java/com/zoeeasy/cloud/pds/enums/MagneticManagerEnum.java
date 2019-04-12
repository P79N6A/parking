package com.zoeeasy.cloud.pds.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Date: 2018/8/23
 * @author: wh
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MagneticManagerEnum {

    //设备管理器
    MAGNETIC_MANAGER_SERIALNUMBER_EMPTY(10001, "设备管理器序列号为空"),
    MAGNETIC_MANAGER_PROVIDER_EMPTY(10002, "设备管理器厂商为空"),
    MAGNETIC_MANAGER_LATITUDE_EMPTY(10003, "设备管理器纬度为空"),
    MAGNETIC_MANAGER_LONGITUDE_EMPTY(10004, "设备管理器经度为空"),
    MAGNETIC_MANAGER_SERIALNUMBER_EXISTS(10005, "设备管理器序列号已存在"),
    MAGNETIC_MANAGER_ID_EMPTY(10006, "设备管理器ID为空"),
    MAGNETIC_MANAGER_NOT_FOUND(10007, "设备管理器不存在"),
    MAGNETIC_MANAGER_FILE_WRONG(10009, "文件导入名错误"),
    MAGNETIC_MANAGER_SERIALNUMBER_WRONG(10010, "设备管理器序列号不匹配"),
    MAGNETIC_MANAGER_LATITUDE_WRONG(10011, "设备管理器纬度不匹配"),
    MAGNETIC_MANAGER_LONGITUDE_WRONG(10012, "设备管理器经度不匹配"),
    MAGNETIC_MANAGER_ADDRESS_WRONG(10013, "设备管理器安装地址不匹配"),;

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
    public static MagneticManagerEnum parse(Integer value) {
        if (value != null) {
            MagneticManagerEnum[] array = values();
            for (MagneticManagerEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
